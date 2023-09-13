package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {

        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/lk")
    public String getUserPage2(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userDetailsService.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("admin/{id}")
    public String showUserById(@PathVariable("id") Integer id, ModelMap modelMap) {
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
    public String addUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roless", roleService.allRoles());
        System.out.println("_____________________________________________________");
        System.out.println(user.toString());
        System.out.println(roleService.allRoles());
        System.out.println("_____________________________________________________");
        return "addUser";
    }


//    @PostMapping(value = "admin/add")
//    public String postAddUser(@ModelAttribute("user") User user,
//                              @RequestParam(required = false) String  roleAdmin,
//                              @RequestParam(required = false) String  roleUser) {
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleService.getRoleByName(roleAdmin));
//        roles.add(roleService.getRoleByName(roleUser));
//        user.setRoles(roles);
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

    @PostMapping(value = "admin/add")
    public String postAddUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        System.out.println("_____________________________________________________");
        System.out.println(user.toString());
        System.out.println("_____________________________________________________");
        return "redirect:/admin";
    }

    @GetMapping (value = "admin/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        Set<Role> roles = roleService.allRoles();
        System.out.println(roles.toString());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "editUser";
    }


    @PatchMapping("admin/edit")
    public String postEditUser(@ModelAttribute("user") User user) {

        userService.update(user);

        return "redirect:/admin";
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}