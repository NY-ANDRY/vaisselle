package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
