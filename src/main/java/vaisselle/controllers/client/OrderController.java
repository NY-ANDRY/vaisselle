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
import vaisselle.models.tables.Order;
import vaisselle.services.OrderService;

@Controller
@RequestMapping("/client/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

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

}
