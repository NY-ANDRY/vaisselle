package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vaisselle.models.tables.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}