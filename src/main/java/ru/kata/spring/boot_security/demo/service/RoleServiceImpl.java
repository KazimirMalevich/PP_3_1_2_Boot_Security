package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public Set<Role> allRoles() {
        return roleDAO.allRoles();
    }

    @Override
    public Role getDefaultRole() {
        return roleDAO.getDefaultRole();
    }

    @Override
    public void addRole(Role role) {
        roleDAO.addRoles(role);
    }
}
