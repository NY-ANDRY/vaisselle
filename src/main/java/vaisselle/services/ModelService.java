package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.Model;
import vaisselle.repositories.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    public Model getModel(Long idModel) {
        return modelRepository.findById(idModel).orElse(null);
    }

    public Model updateModel(Model model) {
        return modelRepository.save(model);
    }

    public void deleteModel(Long idModel) {
        modelRepository.deleteById(idModel);
    }
}
