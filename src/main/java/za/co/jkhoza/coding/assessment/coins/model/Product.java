package za.co.jkhoza.coding.assessment.coins.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "productCode"))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String productCode;

    @Column(unique = true)
    private String name;

    private double price;

    private long quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public enum ProductCategory {

        BEEVERAGES("Cold Drinks"),
        DRY_FRUITS("Dry Fruits"),
        BACKERY("Baked Products"),
        SNACKS("Snackie stuff");

        private final String value;

        ProductCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
