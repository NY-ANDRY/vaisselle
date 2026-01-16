package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vaisselle.models.tables.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
