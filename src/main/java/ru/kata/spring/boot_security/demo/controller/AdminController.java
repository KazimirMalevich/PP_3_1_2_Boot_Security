package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserDetailsServiceImp userDetailsServiceImp) {

        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @GetMapping(value = "/lk")
    public String getUserPage2(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userDetailsServiceImp.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("admin/{id}")
    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUser(id));
        return "userPage";
    }

    @GetMapping(value = "/admin")
    public String welcome() {
        return "redirect:/admin/all";
    }

    @GetMapping(value = "admin/all")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsersPage";
    }

    @GetMapping(value = "admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "admin/add")
    public String postAddUser(@ModelAttribute("user") User user,
                              @RequestParam(required = false) String  roleAdmin,
                              @RequestParam(required = false) String  roleUser) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(roleAdmin));
        roles.add(roleService.getRoleByName(roleUser));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "admin/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.equals(roleService.getRoleById(1))) {
                model.addAttribute("roleAdmin", true);
            }
            if (role.equals(roleService.getRoleById(2))) {
                model.addAttribute("roleUser", true);
            }

        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "admin/edit")
    public String postEditUser(@ModelAttribute("user") User user,
                               @RequestParam(required = false) String roleAdmin,
                               @RequestParam(required = false) String roleUser) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(roleAdmin));
        roles.add(roleService.getRoleByName(roleUser));
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}