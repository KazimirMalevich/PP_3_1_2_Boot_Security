package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
public interface RoleRepository {
    Role getRoleByName(String name);
    Role getRoleById(Integer id);
    List<Role> allRoles();
    Role getDefaultRole();
}
