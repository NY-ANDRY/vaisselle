package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Cart;
import vaisselle.models.tables.CartDetail;
import vaisselle.models.tables.DiscountCart;
import vaisselle.repositories.CartDetailRepository;
import vaisselle.repositories.CartRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscountCartService discountCartService;

    public Cart makeCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        double total = 0;

        DiscountCart dc = discountCartService.getAllDiscountCart().get(0);
        double discount = dc.getDiscount();
        double nbDiscount = dc.getNb();

        double totalQtt = 0;

        for (CartDetail cd : cart.getDetails()) {
            totalQtt += cd.getQtt();

            double curPrice = cd.getProduct().getLocation() * cd.getQtt();
            if (cd.getProduct().getNbDiscount() <= cd.getQtt()) {
                curPrice = curPrice - ((curPrice / 100) * cd.getProduct().getDiscount());
                cd.setTotal(curPrice);
                System.out.println("ok");
            } else {
                cd.setTotal(cd.getProduct().getLocation());
                System.out.println("no");
            }
            total += curPrice;

        }

        if (totalQtt >= nbDiscount) {
            total = total - ((total / 100) * discount);
            cart.setDiscount(discount);
        }

        cart.setTotal(total);

        return cart;
    }

    public Cart addDetails(Cart cart, Long idProduct, Integer qtt) {
        boolean contain = false;

        for (CartDetail cd : cart.getDetails()) {
            if (cd.getProduct().getId().equals(idProduct)) {
                cd.setQtt(cd.getQtt() + qtt);
                cartDetailRepository.save(cd);
                contain = true;
                break;
            }
        }

        if (!contain) {
            CartDetail cd = new CartDetail();
            cd.setCart(cart);
            cd.setProduct(productService.getProduct(idProduct));
            cd.setQtt(qtt);
            cartDetailRepository.save(cd);
            cart.getDetails().add(cd);
        }

        return cartRepository.save(cart);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
