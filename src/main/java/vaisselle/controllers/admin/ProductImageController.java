package vaisselle.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.models.tables.ProductImage;
import vaisselle.services.ProductImageService;

@Controller
@RequestMapping("/admin/productsImages")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    public ProductImageController() {
    }

    @GetMapping("/{id}/default")
    public String setDefault(@PathVariable("id") Long id) {
        ProductImage pi = productImageService.setDefaultProductImage(id);
        return "redirect:/admin/products/" + pi.getProduct().getId();
    }

}
