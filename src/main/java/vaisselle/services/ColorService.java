package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.Color;
import vaisselle.repositories.ColorRepository;

import java.util.List;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color saveColor(Color color) {
        return colorRepository.save(color);
    }

    public Color getColor(Long idColor) {
        return colorRepository.findById(idColor).orElse(null);
    }

    public Color updateColor(Color color) {
        return colorRepository.save(color);
    }

    public void deleteColor(Long idColor) {
        colorRepository.deleteById(idColor);
    }
}
