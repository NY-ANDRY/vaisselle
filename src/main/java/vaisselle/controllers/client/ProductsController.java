package vaisselle.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.services.ProductService;

@Controller
@RequestMapping("/client/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService userService) {
        this.productService = userService;
    }

    @GetMapping("")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "client/products/index";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) {
        var user = productService.getProduct(id);
        model.addAttribute("user", user);
        return "admin/users/user";
    }

}
