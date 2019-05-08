package pl.mypocket;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.mypocket.Service.UserService;
import pl.mypocket.model.Product;
import pl.mypocket.model.ProductList;
import pl.mypocket.model.User;
import pl.mypocket.repository.ProductRepository;
import pl.mypocket.repository.UserRepository;


@SpringBootApplication
public class MypocketV3SpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MypocketV3SpringBootApplication.class, args);

        UserService userService = ctx.getBean(UserService.class);

        User user = new User("Mike", "aa@wp.pl", "haslo1");
        User user2 = new User("Pati", "aa2@wp.pl", "haslo13");
        User badUser = new User("Mike", "badMail", null);


        
        ctx.close();
    }

}
