package vaisselle.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.models.tables.Product;
import vaisselle.models.tables.Promotion;
import vaisselle.services.ProductPromotionService;
import vaisselle.services.ProductService;
import vaisselle.services.PromotionService;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPromotionService productPromotionService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "admin/promotions/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "admin/promotions/create";
    }

    @PostMapping("")
    public String store(Promotion promotion) {
        promotionService.savePromotion(promotion);
        return "redirect:/admin/promotions";
    }

    @GetMapping("/{id}/products")
    public String manageProducts(@PathVariable Long id, Model model) {
        Promotion promotion = promotionService.getPromotion(id);
        model.addAttribute("promotion", promotion);
        // We need a list of all products, and for each, know if it's assigned.
        // For simplicity in Thymeleaf, we can pass all products and a helper tool or
        // handle it in the view with a custom object or just checking status if handy.
        // But cleaner is to check in the controller or service.
        // However, iterating all products in view and checking service might be N+1 if
        // not careful,
        // but for this task size it might be acceptable.
        // A better way: Pass a wrapper or map.
        // Let's pass all products and the service/util to check.

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("productPromotionService", productPromotionService); // To check in view:
                                                                                // ${productPromotionService.isProductAssigned(product.id,
                                                                                // promotion.id)}

        return "admin/promotions/products";
    }

    @PostMapping("/{id}/products/{productId}/assign")
    public String assignProduct(@PathVariable Long id, @PathVariable Long productId) {
        Promotion promotion = promotionService.getPromotion(id);
        Product product = productService.getProduct(productId);
        productPromotionService.assignProductToPromotion(product, promotion);
        return "redirect:/admin/promotions/" + id + "/products";
    }

    @PostMapping("/{id}/products/{productId}/remove")
    public String removeProduct(@PathVariable Long id, @PathVariable Long productId) {
        productPromotionService.removeProductFromPromotion(productId, id);
        return "redirect:/admin/promotions/" + id + "/products";
    }
}
