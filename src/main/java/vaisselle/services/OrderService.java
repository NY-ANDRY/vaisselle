package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Cart;
import vaisselle.models.tables.CartDetail;
import vaisselle.models.tables.Order;
import vaisselle.models.tables.OrderDetail;
import vaisselle.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CartService cartService;

    public Order makeOrder(long idCart) {
        Order result = new Order();
        Cart cart = cartService.makeCart(idCart);

        ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();

        for (CartDetail cartDetail : cart.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setArea(cartDetail.getArea());
            orderDetail.setAreaPrice(cartDetail.getArea().getCost());
            orderDetail.setProduct(cartDetail.getProduct());
            orderDetail.setProductDiscount(cartDetail.getDiscount());
            orderDetail.setProductDiscountValue(cartDetail.getDiscountValue());
            orderDetail.setProductPrice(cartDetail.getProduct().getPrice());
            orderDetail.setQtt(cartDetail.getQtt());
            orderDetail.setTotalProduct(orderDetail.getProductPrice() * orderDetail.getQtt());
            orderDetail.setTotalPrice(cartDetail.getTotal());

            orderDetail.setOrder(result);

            details.add(orderDetail);
        }

        result.setDetails(details);
        result.setDeliveryCost(cart.getTotalDeliveries());
        result.setDiscount(cart.getDiscount());
        result.setProductCost(cart.getTotalProducts());
        result.setTotalCost(cart.getTotalCost());
        result.setUser(cart.getUser());

        save(result);

        return result;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order save(Order order) {
        Order result = orderRepository.save(order);

        if (order.getDetails() == null) {
            return result;
        }

        for (OrderDetail details : order.getDetails()) {
            orderDetailService.save(details);
        }
        return result;
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
