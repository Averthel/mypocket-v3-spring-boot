package pl.mypocket.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product")
    private Long id;
    @Column(name="product_name", nullable = false)
    private String name;
    @Column(name="calories")
    private Double calories;
    @Column(name="details")
    private String details;
    @ManyToMany(mappedBy = "products")
    private List<ProductList> productList;

    public Product(){}

    public Product(String name, Double calories, String details) {
        this.name = name;
        this.calories = calories;
        this.details = details;
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getCalories() != null ? !getCalories().equals(product.getCalories()) : product.getCalories() != null)
            return false;
        if (getDetails() != null ? !getDetails().equals(product.getDetails()) : product.getDetails() != null)
            return false;
        return getProductList() != null ? getProductList().equals(product.getProductList()) : product.getProductList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCalories() != null ? getCalories().hashCode() : 0);
        result = 31 * result + (getDetails() != null ? getDetails().hashCode() : 0);
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        return result;
    }
}
