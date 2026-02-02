package vaisselle.models.tables;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @Column(name = "area_price")
    private double areaPrice;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "total_product")
    private double totalProduct;

    @Column(name = "product_discount")
    private double productDiscount;

    @Column(name = "product_discount_value")
    private double productDiscountValue;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;

    @Column(name = "qtt")
    private Integer qtt;

    @OneToMany(mappedBy = "orderDetail")
    private List<OrderDetailBack> returns;

    public OrderDetail() {
    }

    public double getBackValue() {
        double result = getTotalProduct() - getProductDiscountValue();
        return result;
    }

    public boolean isBack() {
        return returns != null && !returns.isEmpty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQtt() {
        return qtt;
    }

    public void setQtt(Integer qtt) {
        this.qtt = qtt;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getProductDiscountValue() {
        return productDiscountValue;
    }

    public void setProductDiscountValue(double productDiscountValue) {
        this.productDiscountValue = productDiscountValue;
    }

    public double getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(double areaPrice) {
        this.areaPrice = areaPrice;
    }

    public double getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(double totalProduct) {
        this.totalProduct = totalProduct;
    }

}