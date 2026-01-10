package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {
}
