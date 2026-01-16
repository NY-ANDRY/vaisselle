package vaisselle.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vaisselle.models.tables.DiscountCart;
import vaisselle.services.DiscountCartService;
import vaisselle.services.ProductService;

@Controller
@RequestMapping("/admin/discounts")
public class DiscountController {

    @Autowired
    private ProductService productService;
    @Autowired
    private DiscountCartService discountCartService;

    public DiscountController() {
    }

    @GetMapping("")
    public String viewDiscounts(Model model, HttpSession session) {
        DiscountCart dc = discountCartService.getAllDiscountCart().get(0);
        model.addAttribute("paramm", dc);
        model.addAttribute("parammm", productService.getAllProducts().get(0));
        return "admin/discounts/index";
    }

    @PostMapping("")
    public String setCartDiscounts(@RequestParam Double cartNbDiscount, @RequestParam Double cartDiscount,
            @RequestParam Double productsNbDiscount, @RequestParam Double productsDiscount) {

        discountCartService.setDiscounts(cartNbDiscount, cartDiscount);
        productService.setAllProductDiscount(productsNbDiscount, productsDiscount);

        return "redirect:/admin/discounts";
    }

}
