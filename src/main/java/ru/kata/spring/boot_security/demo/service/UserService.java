package ru.kata.spring.boot_security.demo.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(Integer id);

    Optional<User> deleteUser(Integer id);

    void update(User user);
}
