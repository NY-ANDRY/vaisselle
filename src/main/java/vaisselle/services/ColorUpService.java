package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.ColorUp;
import vaisselle.repositories.ColorUpRepository;

import java.util.List;

@Service
public class ColorUpService {

    @Autowired
    private ColorUpRepository colorUpRepository;

    public List<ColorUp> findAll() {
        return colorUpRepository.findAll();
    }

    public ColorUp findById(Long id) {
        return colorUpRepository.findById(id).orElseThrow();
    }

    public ColorUp save(ColorUp cart) {
        return colorUpRepository.save(cart);
    }

    public void deleteById(Long id) {
        colorUpRepository.deleteById(id);
    }
}
