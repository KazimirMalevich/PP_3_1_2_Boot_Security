package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/")
public class BoostrapController {
    private final UserService userService;
    private final RoleService roleService;

    public BoostrapController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roless", roleService.allRoles());
        return "adminPage";
    }

//        @GetMapping(value = "/admin/add")
//    public String addUser(ModelMap model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        model.addAttribute("roless", roleService.allRoles());
//        return "adminPage";
//    }

    @PostMapping(value = "admin/add")
    public String postAddUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

        @GetMapping ("admin/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping (value = "admin/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        Set<Role> roles = roleService.allRoles();
        System.out.println(roles.toString());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "adminPage";
    }


    @PatchMapping("admin/edit")
    public String postEditUser(@ModelAttribute("user") User user) {

        userService.update(user);

        return "redirect:/admin";
    }
}

