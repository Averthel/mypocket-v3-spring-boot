package pl.mypocket.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_list")
public class ProductList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_list")
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "products_list",
            joinColumns = {@JoinColumn(name="list_id", referencedColumnName="id_list")},
            inverseJoinColumns = {@JoinColumn(name="product_id", referencedColumnName="id_product")}
    )
    private List<Product> products = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ProductList(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                ", user=" + user.getUsername() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList)) return false;

        ProductList that = (ProductList) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getProducts() != null ? !getProducts().equals(that.getProducts()) : that.getProducts() != null)
            return false;
        return getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}
