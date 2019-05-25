package pl.mypocket.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mypocket.model.User;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        User user = new User();
        model.addAttribute(user);
        return "index";
    }


    @PostMapping("/")
    public String consumeForm(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
        }
        return "index";
    }

    @RequestMapping("/userpanel")
    public String userPanel(){
        return "userpanel";
    }


}
