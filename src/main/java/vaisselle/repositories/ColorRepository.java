package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vaisselle.models.tables.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
