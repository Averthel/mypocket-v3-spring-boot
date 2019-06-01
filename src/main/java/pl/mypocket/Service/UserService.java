package pl.mypocket.Service;


import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mypocket.model.User;
import pl.mypocket.model.UserRole;
import pl.mypocket.repository.UserRepository;
import pl.mypocket.repository.UserRoleRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        try {
            userRepository.save(user);
            return true;
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            errors.forEach(err -> System.err.println(
                    err.getPropertyPath() + " " +
                            err.getInvalidValue() + " " +
                            err.getMessage()));
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    /*
    public void removeUser(User user) {
        try {
            userRepository.delete(user);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            errors.forEach(err -> System.err.println(
                    err.getPropertyPath() + " " +
                            err.getInvalidValue() + " " +
                            err.getMessage()));
        }
    }

    */

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
