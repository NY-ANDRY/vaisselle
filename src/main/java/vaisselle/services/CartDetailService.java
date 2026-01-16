package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.CartDetail;
import vaisselle.repositories.CartDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
