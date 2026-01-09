package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
