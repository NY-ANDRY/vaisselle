package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.ProductColor;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
}
