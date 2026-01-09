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

    public Product saveProduct(Product user) {
        return productRepository.save(user);
    }

    public Product getProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    public Product updateProduct(Product user) {
        return productRepository.save(user);
    }
}
