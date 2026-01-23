package vaisselle.models.tables;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "area")
    private List<Cart> carts;

    @OneToMany(mappedBy = "area")
    private List<AreaCost> costs;

    public Area() {
    }

    public double getCost() {
        if (costs.size() <= 0) {
            return 0;
        }
        return costs.get(0).getCost();
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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
    public List<AreaCost> getCosts() {
        return costs;
    }

    public void setCosts(List<AreaCost> costs) {
        this.costs = costs;
    }

}
