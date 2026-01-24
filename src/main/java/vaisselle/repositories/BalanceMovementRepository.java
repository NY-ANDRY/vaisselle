package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vaisselle.models.tables.BalanceMovement;

@Repository
public interface BalanceMovementRepository extends JpaRepository<BalanceMovement, Long> {
    @Query("SELECT SUM(bm.amount) FROM BalanceMovement bm WHERE bm.type.movement > 0")
    Double sumValueIn();

    @Query("SELECT SUM(bm.amount) FROM BalanceMovement bm WHERE bm.type.movement < 0")
    Double sumValueOut();
}
