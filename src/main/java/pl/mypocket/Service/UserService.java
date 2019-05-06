package pl.mypocket.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import pl.mypocket.model.User;


@Service
public class UserService {

    private Set<User> users;

    private Validator validator;

    @Autowired
    public UserService(Validator validator) {
        this.validator = validator;
        this.users = new HashSet<>();
    }

    public void addUser(User user){
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        if(errors.hasErrors()){
            System.err.printf("There are errors(%d):\n", errors.getErrorCount());
            for(ObjectError err: errors.getAllErrors()){
                System.err.println(err.getDefaultMessage());
            }
        }else{
            users.add(user);
        }
    }

    public Set<User> getUsers(){
        return this.users;
    }

}
