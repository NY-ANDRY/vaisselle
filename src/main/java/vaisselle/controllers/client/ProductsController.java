package vaisselle.controllers.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vaisselle.services.CategoryService;
import vaisselle.services.ColorService;
import vaisselle.services.ModelService;
import vaisselle.services.ProductService;
import vaisselle.services.SizeService;
import vaisselle.services.TypeService;
import vaisselle.services.VModelService;

@Controller
@RequestMapping("/client/products")
public class ProductsController {

    private final ProductService productService;
    private final VModelService vmodelService;
    private final TypeService typeService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ModelService modelService;

    public ProductsController(ProductService userService, VModelService vmodelService,
            TypeService typeService,
            CategoryService categoryService,
            SizeService sizeService,
            ColorService colorService,
            ModelService modelService) {
        this.productService = userService;
        this.vmodelService = vmodelService;
        this.typeService = typeService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.modelService = modelService;
    }

    @GetMapping("")
    public String viewProducts(
            @RequestParam(required = false) List<Long> typeIds,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minLocation,
            @RequestParam(required = false) Double maxLocation,
            @RequestParam(required = false) List<Long> sizeIds,
            @RequestParam(required = false) List<Long> colorIds,
            @RequestParam(required = false) String keyword,
            Model model) {

        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());

        model.addAttribute("typeIds", typeIds);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("minLocation", minLocation);
        model.addAttribute("maxLocation", maxLocation);
        model.addAttribute("sizeIds", sizeIds);
        model.addAttribute("colorIds", colorIds);
        model.addAttribute("keyword", keyword);

        if (typeIds != null && typeIds.isEmpty()) {
            typeIds = null;
        }

        if (sizeIds != null && sizeIds.isEmpty()) {
            sizeIds = null;
        }

        if (colorIds != null && colorIds.isEmpty()) {
            colorIds = null;
        }

        if ((typeIds != null || categoryId != null || minLocation != null || maxLocation != null || sizeIds != null
                || colorIds != null) || (keyword != null && !keyword.trim().isEmpty())) {
            model.addAttribute("models",
                    vmodelService.getFilteredVModels(typeIds, categoryId, minLocation, maxLocation, sizeIds, colorIds,
                            keyword));
        } else {
            model.addAttribute("models", vmodelService.getAllVModelsDistinct());
        }
        return "client/products/index";
    }

    @GetMapping("/{id}")
    public String viewModel(@PathVariable("id") Long id, Model model) {
        var m = modelService.getModel(id);
        model.addAttribute("model", m);
        return "client/products/details";
    }

}
