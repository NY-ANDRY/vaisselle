package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vaisselle.models.tables.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("""
                SELECT COALESCE(SUM(m.qtt * t.type), 0)
                FROM Movement m
                JOIN m.type t
                WHERE m.product.id = :productId
            """)
    double getStockByProduct(@Param("productId") Long productId);

}
