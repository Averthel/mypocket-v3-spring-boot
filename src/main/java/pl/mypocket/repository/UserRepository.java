package pl.mypocket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mypocket.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
