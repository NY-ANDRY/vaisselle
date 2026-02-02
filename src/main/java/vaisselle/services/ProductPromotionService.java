package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vaisselle.models.tables.Product;
import vaisselle.models.tables.ProductPromotion;
import vaisselle.models.tables.Promotion;
import vaisselle.repositories.ProductPromotionRepository;

@Service
public class ProductPromotionService {

    @Autowired
    private ProductPromotionRepository productPromotionRepository;

    @Transactional
    public void assignProductToPromotion(Product product, Promotion promotion) {
        if (!productPromotionRepository.existsByProductIdAndPromotionId(product.getId(), promotion.getId())) {
            ProductPromotion pp = new ProductPromotion();
            pp.setProduct(product);
            pp.setPromotion(promotion);
            productPromotionRepository.save(pp);
        }
    }

    @Transactional
    public void removeProductFromPromotion(Long productId, Long promotionId) {
        productPromotionRepository.deleteByProductIdAndPromotionId(productId, promotionId);
    }

    public boolean isProductAssigned(Long productId, Long promotionId) {
        return productPromotionRepository.existsByProductIdAndPromotionId(productId, promotionId);
    }
}
