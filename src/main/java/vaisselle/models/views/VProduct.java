package vaisselle.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM v_products")
@Immutable
public class VProduct {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "product_location")
    private Double productLocation;

    @Column(name = "deleted_at")
    private java.time.LocalDateTime deletedAt;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "model_description")
    private String modelDescription;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "size_name")
    private String sizeName;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "stock")
    private Double stock;

    /* ================= GETTERS ================= */

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public java.time.LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Long getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Double getProductLocation() {
        return productLocation;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public Long getColorId() {
        return colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public Double getStock() {
        return stock;
    }
}
