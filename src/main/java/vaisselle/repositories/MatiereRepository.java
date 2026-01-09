package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}
