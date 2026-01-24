package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vaisselle.models.tables.BalanceMovementType;

@Repository
public interface BalanceMovementTypeRepository extends JpaRepository<BalanceMovementType, Long> {
}
