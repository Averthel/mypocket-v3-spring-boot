package pl.mypocket.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mypocket.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductControllerMvc {

    private ProductRepository productRepository;

    @Autowired
    public ProductControllerMvc(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
}
