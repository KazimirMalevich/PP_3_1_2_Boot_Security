package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleDAO {
    Role getRoleByName(String name);
    Role getRoleById(Integer id);
    Set<Role> allRoles();
    Role getDefaultRole();
    void addRoles(Role role);
}
