package vaisselle.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vaisselle.models.views.VModel;

public interface VModelRepository extends JpaRepository<VModel, Long> {

    @Query(value = "SELECT DISTINCT ON (model_id) * FROM v_models ORDER BY model_id", nativeQuery = true)
    List<VModel> findAllDistinctByModelId(Pageable pageable);

    default List<VModel> findAllDistinctByModelId() {
        return findAllDistinctByModelId(PageRequest.of(0, 50));
    }

}
