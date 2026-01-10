package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.ModelType;

public interface ModelCategoryRepository extends JpaRepository<ModelType, Long> {
}
