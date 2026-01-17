package vaisselle.models.tables;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "t_products")
@SQLRestriction("deleted_at IS NULL")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", nullable = false)
    private double location;

    @Column(name = "discount", nullable = true)
    private Double discount;

    @Column(name = "nb_discount", nullable = true)
    private Double nbDiscount;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "product")
    private List<Movement> movements;

    @Transient
    private String image;

    @Transient
    private double stock;

    @Transient
    private List<Product> variantsStatic;

    public Product() {
    }

    public double getPrice() {
        double result = getLocation();
        double augmentation = getAugmentation();

        if (augmentation <= 0) {
            return result;
        }

        result = result + ((result / 100) * augmentation);
        return result;
    }

    public double getAugmentation() {
        double augmentation = 0;
        for (ColorUp colorUp : getColor().getUp()) {
            if (colorUp.getValue() > augmentation) {
                augmentation = colorUp.getValue();
            }
        }
        return augmentation;
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

    public List<Product> getVariants() {
        List<Product> result = new ArrayList<Product>();
        for (Product product : getModel().getProducts()) {
            result.add(product);
        }
        return result;
    }

    public List<Product> getOtherVariants() {
        List<Product> result = new ArrayList<Product>();
        for (Product product : getModel().getProducts()) {
            if (product.getId() == getId()) {
                continue;
            }
            result.add(product);
        }
        return result;
    }

    public List<Color> getColors() {
        List<Color> result = new ArrayList<Color>();
        for (Product product : getModel().getProducts()) {
            if (product.getId() == getId()) {
                continue;
            }
            result.add(product.getColor());
        }
        return result;
    }

    public List<Size> getSizes() {
        List<Size> result = new ArrayList<Size>();
        for (Product product : getModel().getProducts()) {
            if (product.getId() == getId()) {
                continue;
            }
            result.add(product.getSize());
        }
        return result;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
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

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
                + ", model=" + model + ", color=" + color + ", size=" + size + ", images=" + images + ", deletedAt="
                + deletedAt + ", image=" + image + "]";
    }

    public List<Product> getVariantsStatic() {
        return variantsStatic;
    }

    public void setVariantsStatic(List<Product> variantsStatic) {
        this.variantsStatic = variantsStatic;
    }

}
