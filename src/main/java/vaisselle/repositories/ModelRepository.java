package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vaisselle.models.tables.Model;
import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query("""
                SELECT m FROM Model m
                WHERE (:typeId IS NULL OR m.type.id = :typeId)
                AND (:categoryId IS NULL OR m.category.id = :categoryId)
            """)
    List<Model> filter(@Param("typeId") Long typeId, @Param("categoryId") Long categoryId);
}