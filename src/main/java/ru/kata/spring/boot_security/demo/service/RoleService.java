package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);

    Role getRoleById(Integer id);

    Set<Role> allRoles();

    Role getDefaultRole();

    void addRole(Role role);
}
