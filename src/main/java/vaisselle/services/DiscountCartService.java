package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.DiscountCart;
import vaisselle.repositories.DiscountCartRepository;

import java.util.List;

@Service
public class DiscountCartService {

    private final DiscountCartRepository discountCartRepository;

    public DiscountCartService(DiscountCartRepository discountCartRepository) {
        this.discountCartRepository = discountCartRepository;
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
