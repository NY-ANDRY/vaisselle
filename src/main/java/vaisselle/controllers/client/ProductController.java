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
import vaisselle.services.SizeService;
import vaisselle.services.TypeService;
import vaisselle.services.VProductService;

@Controller
@RequestMapping("/client/products")
public class ProductController {

    private final VProductService vmodelService;
    private final TypeService typeService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ModelService modelService;

    public ProductController(VProductService vmodelService,
            TypeService typeService,
            CategoryService categoryService,
            SizeService sizeService,
            ColorService colorService,
            ModelService modelService) {
        this.vmodelService = vmodelService;
        this.typeService = typeService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.modelService = modelService;
    }

    @GetMapping("")
    public String viewProducts(
            @RequestParam(required = false) Long typeId,
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

        model.addAttribute("typeId", typeId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("minLocation", minLocation);
        model.addAttribute("maxLocation", maxLocation);
        model.addAttribute("sizeIds", sizeIds);
        model.addAttribute("colorIds", colorIds);
        model.addAttribute("keyword", keyword);

        if (sizeIds != null && sizeIds.isEmpty()) {
            sizeIds = null;
        }

        if (colorIds != null && colorIds.isEmpty()) {
            colorIds = null;
        }

        if ((typeId != null || categoryId != null || minLocation != null || maxLocation != null || sizeIds != null
                || colorIds != null) || (keyword != null && !keyword.trim().isEmpty())) {
            model.addAttribute("models",
                    vmodelService.getFilteredVModels(typeId, categoryId, minLocation, maxLocation, sizeIds, colorIds,
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
