package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vaisselle.models.tables.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
