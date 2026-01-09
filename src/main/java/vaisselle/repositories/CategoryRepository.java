package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
