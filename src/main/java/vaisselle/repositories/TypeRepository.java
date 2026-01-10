package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
