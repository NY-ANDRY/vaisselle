package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaisselle.models.tables.Size;
import vaisselle.repositories.SizeRepository;

import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public SizeService() {
    }

    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    public Size saveSize(Size size) {
        return sizeRepository.save(size);
    }

    public Size getSize(Long idSize) {
        return sizeRepository.findById(idSize).orElse(null);
    }

    public Size updateSize(Size size) {
        return sizeRepository.save(size);
    }

    public void deleteSize(Long idSize) {
        sizeRepository.deleteById(idSize);
    }
}
