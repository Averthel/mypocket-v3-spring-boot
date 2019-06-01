package pl.mypocket.controller.web;


import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    /*
    @GetMapping("/")
    public String check(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName.equals("anonymousUser")) {
            model.addAttribute("login", "Zaloguj się");
        } else {
            model.addAttribute("logout", "Wyloguj się");
        }
        return "index";
    }
    */



    @RequestMapping("/userpanel")
    public String userPanel() {
        return "userpanel";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login_form";
    }


}
