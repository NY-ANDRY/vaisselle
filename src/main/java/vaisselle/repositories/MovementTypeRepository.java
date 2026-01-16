package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vaisselle.models.tables.MovementType;

public interface MovementTypeRepository extends JpaRepository<MovementType, Long> {
}
