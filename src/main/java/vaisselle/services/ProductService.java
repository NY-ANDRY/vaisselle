package vaisselle.services;

import org.springframework.stereotype.Service;

import vaisselle.models.tables.Model;
import vaisselle.repositories.ModelRepository;

import java.util.List;

@Service
public class ProductService {

    private final ModelRepository productRepository;
    public ProductService(ModelRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Model> getAllProducts() {
        return productRepository.findAll();
    }

    public Model saveProduct(Model user, List<Long> categoryIds) {
        return productRepository.save(user);
    }

    public Model getProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    public Model updateProduct(Model user) {
        return productRepository.save(user);
    }
}
