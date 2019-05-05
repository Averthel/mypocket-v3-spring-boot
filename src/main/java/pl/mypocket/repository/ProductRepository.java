package pl.mypocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mypocket.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
