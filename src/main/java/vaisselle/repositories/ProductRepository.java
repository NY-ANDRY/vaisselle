package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vaisselle.models.tables.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
                SELECT p FROM Product p
                WHERE (:categoryId IS NULL OR p.model.category.id = :categoryId)
                AND (:typeId IS NULL OR p.model.type.id = :typeId)
                AND (:colorId IS NULL OR p.color.id = :colorId)
                AND (:sizeId IS NULL OR p.size.id = :sizeId)
            """)
    List<Product> filter(
            @Param("categoryId") Long categoryId,
            @Param("typeId") Long typeId,
            @Param("colorId") Long colorId,
            @Param("sizeId") Long sizeId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.nbDiscount = :nb, p.discount = :discount")
    int setAllDiscounts(@Param("nb") Double nb, @Param("discount") Double discount);

}
