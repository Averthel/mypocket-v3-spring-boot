package pl.mypocket.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mypocket.Service.UserService;
import pl.mypocket.model.User;
import pl.mypocket.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserControllerMvc {

    private UserService userService;


    @Autowired
    public UserControllerMvc(UserService userService) {
        this.userService = userService;
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
}

