package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(Integer id) {
        return entityManager.find(User.class, id);
    }


    public Optional<User> deleteUser(Integer id) {
        Optional<User> user = Optional.ofNullable(getUser(id));
        entityManager.remove(user.orElseThrow(() -> new NullPointerException("User not found")));
        return user;
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager
                .createQuery("select u from User u left join fetch u.roles where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();

    }
}

