package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserDAO {
    Set<User> getAllUsers();

    void saveUser(User user);

    User getUser(Integer id);

    Optional<User> deleteUser(Integer id);

    void update(User user);

    User getUserByUsername(String username);
}
