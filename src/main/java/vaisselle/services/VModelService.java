package vaisselle.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vaisselle.models.views.VModel;
import vaisselle.repositories.VModelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

@Service
public class VModelService {

    private final VModelRepository vModelRepository;

    private final EntityManager entityManager;

    public VModelService(VModelRepository vModelRepository, EntityManager entityManager) {
        this.vModelRepository = vModelRepository;
        this.entityManager = entityManager;
    }

    public List<VModel> getAllVModelsDistinct() {
        return vModelRepository.findAllDistinctByModelId(PageRequest.of(0, 50));
    }

    @SuppressWarnings("unchecked")
    public List<VModel> getFilteredVModels(Long matiereId, List<Long> categoryIds, Double minLocation,
            Double maxLocation) {

        StringBuilder sql = new StringBuilder("SELECT DISTINCT ON (model_id) * FROM v_models WHERE 1=1");

        if (matiereId != null) {
            sql.append(" AND matiere_id = :matiereId");
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sql.append(" AND category_id IN (:categoryIds)");
        }
        if (minLocation != null) {
            sql.append(" AND model_location >= :minLocation");
        }
        if (maxLocation != null) {
            sql.append(" AND model_location <= :maxLocation");
        }

        sql.append(" ORDER BY model_id");

        Query query = entityManager.createNativeQuery(sql.toString(), VModel.class);

        if (matiereId != null) {
            query.setParameter("matiereId", matiereId);
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            query.setParameter("categoryIds", categoryIds);
        }
        if (minLocation != null) {
            query.setParameter("minLocation", minLocation);
        }
        if (maxLocation != null) {
            query.setParameter("maxLocation", maxLocation);
        }

        return query.getResultList();
    }

    public List<VModel> getAllVModels() {
        return vModelRepository.findAll();
    }

    public VModel getVModel(Long idVModel) {
        return vModelRepository.findById(idVModel).orElse(null);
    }
}
