package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RootController {
    private final UserService userService;
    private final RoleService roleService;

    public RootController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "loginPage";
    }

    @GetMapping(value = "/registration")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "regUser";
    }

    @PostMapping(value = "/registration")
    public String regUser(@ModelAttribute("user") User user,
                          @RequestParam(required = false) String roleAdmin,
                          @RequestParam(required = false) String roleUser) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(roleAdmin));
        roles.add(roleService.getRoleByName(roleUser));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}



