package pl.mypocket.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mypocket.Service.ProductService;
import pl.mypocket.model.Product;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductControllerMvc {

    private ProductService productService;

    @Autowired
    public ProductControllerMvc(ProductService productService){
       this.productService = productService;
    }

    @GetMapping("/addProdcut")
    public String register(Model model){
        model.addAttribute("prodcut", new Product());
        return "index";
    }


    @PostMapping("/addProduct")
    public String addProductToDatabase(@Valid @ModelAttribute Product product, BindingResult result, Model model){
        boolean isProductAdded = productService.addProductToDatabase(product);
        if(isProductAdded){
            model.addAttribute("message", "Produkt "+ product.getName() + " dodany");
        }else if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "index";
        }else{
            List<Product> products = productService.findAll();
            for(Product p: products){
                if(p.getName().equals(product.getName())){
                    model.addAttribute("message", "Produkt o podanej nazwie juz istnieje w bazie danych");
                }
            }
        }
        return "index";
    }
}
