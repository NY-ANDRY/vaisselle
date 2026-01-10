package vaisselle.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vaisselle.models.views.VProduct;

public interface VProductRepository extends JpaRepository<VProduct, Long> {

    @Query(value = "SELECT DISTINCT ON (model_id) * FROM v_products ORDER BY model_id", nativeQuery = true)
    List<VProduct> findAllDistinctByModelId(Pageable pageable);

    default List<VProduct> findAllDistinctByModelId() {
        return findAllDistinctByModelId(PageRequest.of(0, 50));
    }

}
