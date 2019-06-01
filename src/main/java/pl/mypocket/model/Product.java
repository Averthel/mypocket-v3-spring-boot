package pl.mypocket.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "calories")
    private Double calories;
    @Column(name = "producer")
    private String producer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product() { }

    public Product(String name, Double calories, String producer) {
        this.name = name;
        this.calories = calories;
        this.producer = producer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", producer='" + producer + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getId() != product.getId()) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getCalories() != null ? !getCalories().equals(product.getCalories()) : product.getCalories() != null)
            return false;
        if (getProducer() != null ? !getProducer().equals(product.getProducer()) : product.getProducer() != null)
            return false;
        return getUser() != null ? getUser().equals(product.getUser()) : product.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCalories() != null ? getCalories().hashCode() : 0);
        result = 31 * result + (getProducer() != null ? getProducer().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}
