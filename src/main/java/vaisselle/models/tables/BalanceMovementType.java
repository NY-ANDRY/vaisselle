package vaisselle.models.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_balance_movement_type")
public class BalanceMovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "movement")
    private int movement; // 1 or -1

    public BalanceMovementType() {
    }

    public BalanceMovementType(Long id, String name, String description, int movement) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.movement = movement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
