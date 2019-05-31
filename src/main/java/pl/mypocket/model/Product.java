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
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "calories")
    private Double calories;
    @Column(name = "details")
    private String details;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product() {
    }

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
                ", details='" + details + '\'' +
                '}';
    }

}
