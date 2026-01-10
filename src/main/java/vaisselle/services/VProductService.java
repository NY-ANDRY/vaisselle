package vaisselle.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vaisselle.models.views.VProduct;
import vaisselle.repositories.VProductRepository;

import java.util.List;

@Service
public class VProductService {

    private final VProductRepository vProductRepository;

    public VProductService(VProductRepository vModelRepository) {
        this.vProductRepository = vModelRepository;
    }

    public List<VProduct> getAllVModelsDistinct() {
        return vProductRepository.findAllDistinctByModelId(PageRequest.of(0, 50));
    }

    public List<VProduct> getFilteredVModels(
            Long typeId,
            Long categoryId,
            Double minLocation,
            Double maxLocation,
            List<Long> sizeIds,
            List<Long> colorIds,
            String keyword) {

        return vProductRepository.filter(
                typeId, categoryId, minLocation, maxLocation, sizeIds, colorIds, keyword);
    }

    public List<VProduct> getAllVModels() {
        return vProductRepository.findAll();
    }

    public VProduct getVModel(Long idVModel) {
        return vProductRepository.findById(idVModel).orElse(null);
    }
}
