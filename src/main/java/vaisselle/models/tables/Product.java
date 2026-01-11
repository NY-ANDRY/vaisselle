package vaisselle.models.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "t_products")
@SQLRestriction("deleted_at IS NULL")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = true)
    private double location;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "idSize")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "idColor")
    private Color color;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Transient
    private String image;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getDefaultImageUrl() {
        if (images == null || images.isEmpty()) {
            return null;
        }
        return images.stream()
                .filter(ProductImage::isDefault)
                .map(ProductImage::getUrl)
                .findFirst()
                .orElse(images.get(0).getUrl());
    }

    public String getImage() {
        if (image == null) {
            image = getDefaultImageUrl();
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
