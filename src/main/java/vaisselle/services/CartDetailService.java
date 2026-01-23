package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.CartDetail;
import vaisselle.repositories.CartDetailRepository;

import java.util.List;

@Service
public class CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    public CartDetail findById(Long id) {
        return cartDetailRepository.findById(id).orElse(null);
    }

    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
