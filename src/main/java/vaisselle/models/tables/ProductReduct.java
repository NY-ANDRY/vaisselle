package vaisselle.models.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_products_reducts")
public class ProductReduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount", nullable = true)
    private Double discount;

    @Column(name = "nb_discount", nullable = true)
    private Double nbDiscount;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public ProductReduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getNbDiscount() {
        return nbDiscount;
    }

    public void setNbDiscount(Double nbDiscount) {
        this.nbDiscount = nbDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
