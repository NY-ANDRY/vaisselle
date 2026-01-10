package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.ModelType;
import vaisselle.repositories.ModelCategoryRepository;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ModelCategoryRepository productCategoryRepository;

    public ProductCategoryService(ModelCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ModelType> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ModelType saveProductCategory(ModelType productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ModelType getProductCategory(Long idProductCategory) {
        return productCategoryRepository.findById(idProductCategory).orElse(null);
    }

    public ModelType updateProductCategory(ModelType productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(Long idProductCategory) {
        productCategoryRepository.deleteById(idProductCategory);
    }
}
