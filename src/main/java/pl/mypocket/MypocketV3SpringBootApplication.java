package pl.mypocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.mypocket.model.User;

@SpringBootApplication
public class MypocketV3SpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MypocketV3SpringBootApplication.class, args);

        User user = new User("Mike", "aa@wp.pl", "haslo");
    }

}
