package vaisselle.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vaisselle.models.views.VProduct;

public interface VProductRepository extends JpaRepository<VProduct, Long> {

    // @Query(value = "SELECT DISTINCT ON (model_id) * FROM v_products ORDER BY
    // model_id", nativeQuery = true)
    // List<VProduct> findAllDistinctByModelId(Pageable pageable);

    // default List<VProduct> findAllDistinctByModelId() {
    // return findAllDistinctByModelId(PageRequest.of(0, 50));
    // }

    @Query(value = "SELECT vp FROM VProduct vp")
    List<VProduct> findAllDistinctByModelId(Pageable pageable);

    default List<VProduct> findAllDistinctByModelId() {
        return findAllDistinctByModelId(PageRequest.of(0, 50));
    }

    @Query("""
                SELECT v FROM VProduct v
                WHERE (:keyword IS NULL OR
                    LOWER(v.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.modelName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.modelDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.typeName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.sizeName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(v.colorName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                )
                AND (:typeId IS NULL OR v.typeId = :typeId)
                AND (:categoryId IS NULL OR v.categoryId = :categoryId)
                AND (:minLocation IS NULL OR v.productLocation >= :minLocation)
                AND (:maxLocation IS NULL OR v.productLocation <= :maxLocation)
                AND (:sizeIds IS NULL OR v.sizeId IN :sizeIds)
                AND (:colorIds IS NULL OR v.colorId IN :colorIds)
            """)
    List<VProduct> filter(
            Long typeId,
            Long categoryId,
            Double minLocation,
            Double maxLocation,
            List<Long> sizeIds,
            List<Long> colorIds,
            String keyword);
}
