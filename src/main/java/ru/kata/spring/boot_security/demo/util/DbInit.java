package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DbInit {
    private final RoleService roleService;
    private final UserService userService;

    public DbInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void addRolesAndUserInit() {
        if (roleService.allRoles().isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getRoleById(1));
            User user = new User("admin", "admin", "admin");
            user.setRoles(roles);
            userService.saveUser(user);


        }
    }
}
