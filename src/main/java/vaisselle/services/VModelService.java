package vaisselle.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vaisselle.models.views.VProduct;
import vaisselle.repositories.VProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VModelService {

    private final VProductRepository vProductRepository;

    private final EntityManager entityManager;

    public VModelService(VProductRepository vModelRepository, EntityManager entityManager) {
        this.vProductRepository = vModelRepository;
        this.entityManager = entityManager;
    }

    public List<VProduct> getAllVModelsDistinct() {
        return vProductRepository.findAllDistinctByModelId(PageRequest.of(0, 50));
    }

    @SuppressWarnings("unchecked")
    public List<VProduct> getFilteredVModels(List<Long> typeIds, Long categoryId, Double minLocation,
            Double maxLocation, List<Long> sizeIds, List<Long> colorIds, String keyword) {

        StringBuilder sql = new StringBuilder("SELECT DISTINCT ON (model_id) * FROM v_products WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (");
            sql.append(" LOWER(product_name) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(model_name) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(model_description) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(category_name) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(type_name) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(size_name) LIKE LOWER(:keyword)");
            sql.append(" OR LOWER(color_name) LIKE LOWER(:keyword)");
            sql.append(" )");
            params.put("keyword", "%" + keyword.trim() + "%");
        }

        if (typeIds != null && !typeIds.isEmpty()) {
            sql.append(" AND type_id IN (:typeIds)");
            params.put("typeIds", typeIds);
        }
        if (categoryId != null) {
            sql.append(" AND category_id = :categoryId");
            params.put("categoryId", categoryId);
        }
        if (minLocation != null) {
            sql.append(" AND product_location >= :minLocation");
            params.put("minLocation", minLocation);
        }
        if (maxLocation != null) {
            sql.append(" AND product_location <= :maxLocation");
            params.put("maxLocation", maxLocation);
        }
        if (sizeIds != null && !sizeIds.isEmpty()) {
            sql.append(" AND size_id IN (:sizeIds)");
            params.put("sizeIds", sizeIds);
        }
        if (colorIds != null && !colorIds.isEmpty()) {
            sql.append(" AND color_id IN (:colorIds)");
            params.put("colorIds", colorIds);
        }

        sql.append(" ORDER BY model_id");

        Query query = entityManager.createNativeQuery(sql.toString(), VProduct.class);

        for (java.util.Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    public List<VProduct> getAllVModels() {
        return vProductRepository.findAll();
    }

    public VProduct getVModel(Long idVModel) {
        return vProductRepository.findById(idVModel).orElse(null);
    }
}
