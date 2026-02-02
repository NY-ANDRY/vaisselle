package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
