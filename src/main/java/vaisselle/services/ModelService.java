package vaisselle.services;

import org.springframework.stereotype.Service;

import vaisselle.models.tables.Model;
import vaisselle.models.tables.ModelType;
import vaisselle.models.tables.Type;
import vaisselle.models.tables.Category;
import vaisselle.repositories.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final TypeService typeService;
    private final CategoryService categoryService;
    private final ProductCategoryService productCategoryService;

    public ModelService(ModelRepository modelRepository,
            TypeService typeService, CategoryService categoryService,
            ProductCategoryService productCategoryService) {
        this.modelRepository = modelRepository;
        this.typeService = typeService;
        this.categoryService = categoryService;
        this.productCategoryService = productCategoryService;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model saveModel(String name, Long categoryId, List<Long> categoryIds) {
        Model result = new Model();
        result.setName(name);

        Category c = categoryService.getCategory(categoryId);
        if (c != null) {
            result.setCategory(c);
        }

        result = modelRepository.save(result);

        if (categoryIds != null) {
            for (Long ci : categoryIds) {
                Type type = typeService.getType(ci);
                if (type != null) {
                    ModelType productCategory = new ModelType();
                    productCategory.setModel(result);
                    productCategory.setType(type);
                    productCategoryService.saveProductCategory(productCategory);
                }
            }
        }

        return result;
    }

    public Model getModel(Long idModel) {
        return modelRepository.findById(idModel).orElse(null);
    }

    public Model updateModel(Model model) {
        return modelRepository.save(model);
    }

    public void deleteModel(Long idModel) {
        modelRepository.deleteById(idModel);
    }
}
