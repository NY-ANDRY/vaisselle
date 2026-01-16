package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Product;
import vaisselle.models.tables.ProductImage;
import vaisselle.repositories.ProductImageRepository;
import vaisselle.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductImageService() {
    }

    public ProductImage setDefaultProductImage(Long id) {
        ProductImage pi = productImageRepository.findById(id).orElseThrow();

        Product p = pi.getProduct();

        for (ProductImage image : p.getImages()) {
            image.setDefault(image.getId().equals(id));
            productImageRepository.save(image);
        }
        productRepository.save(p);

        return pi;
    }

    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    public ProductImage saveProductImage(ProductImage ProductImage) {
        return productImageRepository.save(ProductImage);
    }

    public ProductImage getProductImage(Long idProductImage) {
        return productImageRepository.findById(idProductImage).orElse(null);
    }

    public ProductImage updateProductImage(ProductImage ProductImage) {
        return productImageRepository.save(ProductImage);
    }

    public void deleteProductImage(Long idProductImage) {
        productImageRepository.deleteById(idProductImage);
    }
}
