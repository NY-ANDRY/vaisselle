package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vaisselle.models.tables.OrderDetailBack;

@Repository
public interface OrderDetailBackRepository extends JpaRepository<OrderDetailBack, Long> {
    @Query("SELECT SUM(o.value) FROM OrderDetailBack o")
    Double sumValue();
}
