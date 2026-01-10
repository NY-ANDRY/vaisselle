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
@Table(name = "t_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = true)
    private double location;

    @Column(name = "url", nullable = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Model product;

    @ManyToOne
    @JoinColumn(name = "idSize")
    private Size size;

    @jakarta.persistence.OneToMany(mappedBy = "product")
    private java.util.List<ProductColor> productColors;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String img) {
        this.url = img;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public java.util.List<ProductColor> getProductColors() {
        return productColors;
    }

    public void setProductColors(java.util.List<ProductColor> productColors) {
        this.productColors = productColors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public Model getProduct() {
        return product;
    }

    public void setProduct(Model product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", img=" + url + "]";
    }

}
