package vaisselle.controllers.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vaisselle.services.ProductService;
import vaisselle.services.VModelService;

@Controller
@RequestMapping("/client/products")
public class ProductsController {

    private final ProductService productService;
    private final VModelService vmodelService;
    private final vaisselle.services.MatiereService matiereService;
    private final vaisselle.services.CategoryService categoryService;

    public ProductsController(ProductService userService, VModelService vmodelService,
            vaisselle.services.MatiereService matiereService,
            vaisselle.services.CategoryService categoryService) {
        this.productService = userService;
        this.vmodelService = vmodelService;
        this.matiereService = matiereService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String viewProducts(
            @RequestParam(required = false) Long matiereId,
            @RequestParam(required = false) List<Long> categoryIds,
            @RequestParam(required = false) Double minLocation,
            @RequestParam(required = false) Double maxLocation,
            Model model) {

        model.addAttribute("matieres", matiereService.getAllMatieres());
        model.addAttribute("categories", categoryService.getAllCategories());

        model.addAttribute("matiereId", matiereId);
        model.addAttribute("categoryIds", categoryIds);
        model.addAttribute("minLocation", minLocation);
        model.addAttribute("maxLocation", maxLocation);

        if (categoryIds != null && categoryIds.isEmpty()) {
            categoryIds = null;
        }

        if (matiereId != null || categoryIds != null || minLocation != null || maxLocation != null) {
            model.addAttribute("models",
                    vmodelService.getFilteredVModels(matiereId, categoryIds, minLocation, maxLocation));
        } else {
            model.addAttribute("models", vmodelService.getAllVModelsDistinct());
        }
        return "client/products/index";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) {
        var user = productService.getProduct(id);
        model.addAttribute("user", user);
        return "admin/users/user";
    }

}
