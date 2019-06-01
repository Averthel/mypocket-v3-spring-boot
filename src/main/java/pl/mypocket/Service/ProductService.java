package pl.mypocket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mypocket.model.Product;
import pl.mypocket.repository.ProductRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProductToDatabase(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            errors.forEach(err -> System.err.println(
                    err.getPropertyPath() + " " +
                            err.getInvalidValue() + " " +
                            err.getMessage()
            ));
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
