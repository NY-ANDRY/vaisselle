package vaisselle.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.services.CategoryService;
import vaisselle.services.ColorService;
import vaisselle.services.ModelService;
import vaisselle.services.TypeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vaisselle.services.ProductService;
import vaisselle.services.SizeService;

@Controller("AdminModelsController") // Bean name to avoid conflict with Client ProductsController
@RequestMapping("/admin/models")
public class ModelController {

    private final ModelService modelService;
    private final TypeService matiereService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;

    public ModelController(ModelService productService, TypeService matiereService,
            CategoryService categoryService, SizeService sizeService, ColorService colorService,
            ProductService productServiceBackend) {
        this.modelService = productService;
        this.matiereService = matiereService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productService = productServiceBackend;
    }

    @GetMapping("")
    public String index(org.springframework.ui.Model model, @RequestParam(required = false) Long editId) {
        model.addAttribute("types", matiereService.getAllTypes());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("models", modelService.getAllModels());
        if (editId != null) {
            model.addAttribute("editModel", modelService.getModel(editId));
        }
        return "admin/models/index";
    }

    @GetMapping("/create")
    public String create(org.springframework.ui.Model model) {
        model.addAttribute("types", matiereService.getAllTypes());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/models/create";
    }

    @PostMapping("")
    public String store(@ModelAttribute vaisselle.models.tables.Model model) {
        modelService.saveModel(model);
        return "redirect:/admin/models";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, org.springframework.ui.Model model,
            @RequestParam(required = false) Long editId) {
        model.addAttribute("selectedModel", modelService.getModel(id));
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("redirectUrl", "/admin/models/" + id);

        if (editId != null) {
            model.addAttribute("editProduct", productService.getProduct(editId));
        }

        return "admin/models/show";
    }
}
