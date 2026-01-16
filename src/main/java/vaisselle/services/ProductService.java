package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Product;
import vaisselle.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MovementService movementService;

    public ProductService() {
    }

    public int setAllProductDiscount(Double nb, Double discount) {
        return productRepository.setAllDiscounts(nb, discount);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductWithVariantsStatic(Long idProduct) {
        Product result = productRepository.findById(idProduct).orElse(null);
        List<Product> variants = new ArrayList<Product>();
        for (Product variant : result.getVariants()) {
            variant.setStock(movementService.getStock(variant));
            variants.add(variant);
        }
        result.setVariantsStatic(variants);
        return result;
    }

    public Product getProduct(Long idProduct) {
        Product result = productRepository.findById(idProduct).orElse(null);
        result.setStock(movementService.getStock(result));
        return result;
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long idProduct) {
        Product product = getProduct(idProduct);
        if (product != null) {
            product.setDeletedAt(java.time.LocalDateTime.now());
            productRepository.save(product);
        }
    }

    public List<Product> getFilteredProducts(Long categoryId, Long typeId, Long colorId, Long sizeId) {
        if (categoryId == null && typeId == null && colorId == null && sizeId == null) {
            return getAllProducts();
        }
        return productRepository.filter(categoryId, typeId, colorId, sizeId);
    }
}
