package vaisselle.services;

import org.springframework.stereotype.Service;

import vaisselle.models.tables.Product;
import vaisselle.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
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
}
