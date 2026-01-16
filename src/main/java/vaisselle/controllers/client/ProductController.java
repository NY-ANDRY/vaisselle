package vaisselle.controllers.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vaisselle.services.CategoryService;
import vaisselle.services.ColorService;
import vaisselle.services.ProductService;
import vaisselle.services.SizeService;
import vaisselle.services.TypeService;
import vaisselle.services.VProductService;

@Controller
@RequestMapping("/client/products")
public class ProductController {

    @Autowired
    private VProductService vproductService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ProductService productService;

    public ProductController() {
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
            model.addAttribute("products",
                    vproductService.getFilteredVModels(typeId, categoryId, minLocation, maxLocation, sizeIds, colorIds,
                            keyword));
        } else {
            model.addAttribute("products", vproductService.getAllVModelsDistinct());
        }
        return "client/products/index";
    }

    @GetMapping("/{id}")
    public String viewModel(@PathVariable("id") Long id, Model model) {
        var m = productService.getProductWithVariantsStatic(id);
        model.addAttribute("product", m);
        return "client/products/details";
    }

}
