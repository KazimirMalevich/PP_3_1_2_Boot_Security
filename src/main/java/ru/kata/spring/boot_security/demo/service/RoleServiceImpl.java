package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepo(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Set<Role> allRoles() {
        return roleRepository.allRoles();
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.getDefaultRole();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.addRoles(role);
    }
}
