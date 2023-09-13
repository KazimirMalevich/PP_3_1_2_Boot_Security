package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleRepository {
    Role getRoleByName(String name);
    Role getRoleById(Integer id);
    Set<Role> allRoles();
    Role getDefaultRole();
    void addRoles(Role role);
}
