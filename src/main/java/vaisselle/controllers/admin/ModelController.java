package vaisselle.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.services.CategoryService;
import vaisselle.services.ModelService;
import vaisselle.services.TypeService;

@Controller("AdminModelsController") // Bean name to avoid conflict with Client ProductsController
@RequestMapping("/admin/models")
public class ModelController {

    private final ModelService modelService;
    private final TypeService matiereService;
    private final CategoryService categoryService;

    public ModelController(ModelService productService, TypeService matiereService,
            CategoryService categoryService) {
        this.modelService = productService;
        this.matiereService = matiereService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index(org.springframework.ui.Model model) {
        model.addAttribute("models", modelService.getAllModels());
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
}
