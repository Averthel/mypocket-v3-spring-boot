package pl.mypocket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mypocket.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
