package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = null;
        try {
            role = getEntityManager()
                    .createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Роли с таким именем не существует!");
        }
        return role;
    }

    @Override
    public Role getRoleById(Integer id) {
        return getEntityManager().find(Role.class, id);
    }

    @Override
    public Set<Role> allRoles() {
        return new HashSet<>(getEntityManager()
                .createQuery("select r from Role r", Role.class)
                .getResultList());
    }

    @Override
    public Role getDefaultRole() {
        return getRoleById(2);
    }

    @Override
    public void addRoles(Role role) {
        
        getEntityManager().merge(role);
    }
}
