package vaisselle.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.services.OrderService;

@Controller
@RequestMapping("/admin/orders")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/turnover")
    public String getTurnover(Model model) {
        double totalTurnover = orderService.getTotalTurnoverOk();
        double totalReturns = orderService.getTotalOutOk();
        model.addAttribute("totalTurnover", totalTurnover);
        model.addAttribute("totalReturns", totalReturns);
        model.addAttribute("netTurnover", totalTurnover - totalReturns);
        return "admin/orders/turnover";
    }
}
