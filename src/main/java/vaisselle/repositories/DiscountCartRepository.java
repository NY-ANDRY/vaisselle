package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vaisselle.models.tables.DiscountCart;

public interface DiscountCartRepository extends JpaRepository<DiscountCart, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE DiscountCart d SET d.nb = :nb, d.discount = :discount")
    int setAllDiscounts(@Param("nb") Double nb, @Param("discount") Double discount);

}
