package pl.mypocket.controller.web;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mypocket.Service.ProductService;
import pl.mypocket.Service.UserService;
import pl.mypocket.model.Product;
import pl.mypocket.model.User;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserControllerMvc {

    private UserService userService;
    private ProductService productService;


    @Autowired
    public UserControllerMvc(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login_form";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addWithDefaultRole(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        boolean isAdded = userService.addWithDefaultRole(user);
        if (isAdded) {
            model.addAttribute("message", "Użytkownik " + user.getUsername() + " dodany");
        } else if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "registerForm";
        }else{
            List<User> users = userService.findAll();
            for(User e: users){
                if(e.getUsername().equals(user.getUsername())){
                    model.addAttribute("message", "Użytkownik o podanym nicku juz istenieje");
                }else if(user.geteMail().equals(user.geteMail())){
                    model.addAttribute("message", "Użytkownik o podanym adresie e-mail juz istenieje");
                }else{
                    model.addAttribute("message", "Dodanie użytkownika nie powidło się, przepraszamy.");
                }
            }
        }
        return "index";
    }

    @PostMapping("/addProductToList")
    public String addProductToList(@Valid @ModelAttribute Product product, BindingResult result, Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isProductAdded = productService.addProductToDatabase(product);
        User user = userService.findByUsername(userName);
        if(isProductAdded){
            user.addProductToList(product);
        }
        user.addProductToList(product);
        userService.save(user);
        return "index";
    }

    @GetMapping("/show_list")
    public String showList(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Product> productList = userService.findByUsername(userName).getProductList();
        model.addAttribute("productList", productList);
        return "show_list";
    }
}

