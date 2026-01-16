package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaisselle.models.tables.DiscountCart;
import vaisselle.repositories.DiscountCartRepository;

import java.util.List;

@Service
public class DiscountCartService {

    @Autowired
    private DiscountCartRepository discountCartRepository;

    public DiscountCartService() {
    }

    public int setDiscounts(Double nb, Double discount) {
        return discountCartRepository.setAllDiscounts(nb, discount);
    }

    public List<DiscountCart> getAllDiscountCart() {
        return discountCartRepository.findAll();
    }

    public DiscountCart saveDiscountCart(DiscountCart discountCart) {
        return discountCartRepository.save(discountCart);
    }

    public DiscountCart getDiscountCart(Long idDiscountCart) {
        return discountCartRepository.findById(idDiscountCart).orElse(null);
    }

    public DiscountCart updateDiscountCart(DiscountCart discountCart) {
        return discountCartRepository.save(discountCart);
    }
}
