package vaisselle.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vaisselle.models.tables.BalanceMovement;
import vaisselle.models.tables.Order;
import vaisselle.models.tables.OrderDetail;
import vaisselle.models.tables.OrderDetailBack;
import vaisselle.services.BalanceMovementService;
import vaisselle.services.BalanceMovementTypeService;
import vaisselle.services.OrderDetailBackService;
import vaisselle.services.OrderDetailService;
import vaisselle.services.OrderService;

@Controller
@RequestMapping("/client/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailBackService orderDetailBackService;

    @Autowired
    private BalanceMovementService balanceMovementService;

    @Autowired
    private BalanceMovementTypeService balanceMovementTypeService;

    public OrderController() {
    }

    @GetMapping("")
    public String index(Model model, HttpSession session) {

        return "client/order/index";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable("id") Long id, Model model, HttpSession session) {

        model.addAttribute("order", orderService.findById(id));

        return "client/order/show";
    }

    @PostMapping("")
    public String validate(@RequestParam("idCart") Long idCart, Model model, HttpSession session) {

        Order newOrder = orderService.makeOrder(idCart);

        return "redirect:/client/order/" + newOrder.getId();
    }

    @PostMapping("/return")
    public String returnItem(@RequestParam("idOrderDetail") Long idOrderDetail) {
        OrderDetail detail = orderDetailService.findById(idOrderDetail);
        if (detail != null) {
            OrderDetailBack back = new OrderDetailBack();
            back.setOrderDetail(detail);
            // The value is what the client paid: totalPrice
            back.setValue(detail.getTotalPrice());
            orderDetailBackService.save(back);

            BalanceMovement movement = new BalanceMovement();
            movement.setAmount(detail.getTotalPrice());
            movement.setType(balanceMovementTypeService.findById(2L)); // 2 = Rendu
            balanceMovementService.save(movement);

            return "redirect:/client/order/" + detail.getOrder().getId();
        }
        return "redirect:/client/order";
    }

}
