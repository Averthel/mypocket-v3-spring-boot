package pl.mypocket.model;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "username", unique = true)
    @NotEmpty(message = "{pl.mypocket.model.User.username.NotEmpty}")
    @Size(max=20)
    private String username;
    @Column(name = "e_mail", unique = true)
    @Email
    @NotEmpty(message = "{pl.mypocket.model.User.email.Email}")
    @NotEmpty(message = "{pl.mypocket.model.User.email.NotEmpty}")
    private String eMail;
    @Column(name = "password")
    @Size(min=6, message = "{pl.mypocket.model.User.password.Size}")
    @NotEmpty(message = "{pl.mypocket.model.User.password.NotEmpty}")
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProductList> productList = new ArrayList<>();

    public void addComment(Comment comment){
        comment.setUser(this);
        getComments().add(comment);
    }

    public void addProductList(ProductList productList){
        productList.setUser(this);
        getProductList().add(productList);
    }

    public User() {
    }

    public User(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (geteMail() != null ? !geteMail().equals(user.geteMail()) : user.geteMail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getRoles() != null ? !getRoles().equals(user.getRoles()) : user.getRoles() != null) return false;
        if (getComments() != null ? !getComments().equals(user.getComments()) : user.getComments() != null)
            return false;
        return getProductList() != null ? getProductList().equals(user.getProductList()) : user.getProductList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (geteMail() != null ? geteMail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        return result;
    }
}
