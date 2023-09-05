package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.annotation.PostConstruct;

@Component
public class DbInit {
    private final RoleService roleService;

    public DbInit(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostConstruct
    public void addRolesInit() {
        if (roleService.allRoles().isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
        }
    }
}
