package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.DiscountCart;

public interface DiscountCartRepository extends JpaRepository<DiscountCart, Long> {
}
