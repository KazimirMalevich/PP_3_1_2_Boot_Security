//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.security.Principal;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//    private final UserDetailsService userDetailsService;
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserDetailsService userDetailsService, UserService userService) {
//        this.userDetailsService = userDetailsService;
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "/lk")
//    public String getUserPage2(ModelMap modelMap, Principal principal) {
//        modelMap.addAttribute("user", userDetailsService.loadUserByUsername(principal.getName()));
//        return "adminPage";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
//        modelMap.addAttribute("user", userService.getUser(id));
//        return "adminPage";
//    }
//}
