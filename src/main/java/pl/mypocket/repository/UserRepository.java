package pl.mypocket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mypocket.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class UserRepository {

    private EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void addUser(User user){
        entityManager.persist(user);
    }

    @Transactional
    public void removeUser(User user){
        entityManager.remove(user);
    }
}
