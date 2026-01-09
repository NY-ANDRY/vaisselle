package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.ProductCategory;
import vaisselle.repositories.ProductCategoryRepository;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory getProductCategory(Long idProductCategory) {
        return productCategoryRepository.findById(idProductCategory).orElse(null);
    }

    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(Long idProductCategory) {
        productCategoryRepository.deleteById(idProductCategory);
    }
}
