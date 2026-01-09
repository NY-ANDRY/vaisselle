package vaisselle.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM v_models")
@Immutable
public class VModel {

    @Id
    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "model_location")
    private Double modelLocation;

    @Column(name = "model_url")
    private String modelUrl;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "matiere_id")
    private Long matiereId;

    @Column(name = "matiere_name")
    private String matiereName;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public VModel() {
    }

    public Long getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public Double getModelLocation() {
        return modelLocation;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getMatiereId() {
        return matiereId;
    }

    public String getMatiereName() {
        return matiereName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
