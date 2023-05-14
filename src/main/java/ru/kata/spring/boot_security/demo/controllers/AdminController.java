package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String viewUsers(Model model, Principal principal) {
        model.addAttribute("usersList", userService.listUser());
        model.addAttribute("rolesAtt", roleService.listRole());
        model.addAttribute("newUser", new User());
        model.addAttribute("admin", userService.loadUserByUsername(principal.getName()));
        return "adminPanel";
    }

    @PatchMapping("/{id}")
    public String newEdit(Model model, @ModelAttribute("user") User user, @PathVariable("id") long id) {
        model.addAttribute("user", userService.read(id));
        model.addAttribute("rolesAtt", roleService.listRole());
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String newDelete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String newNewUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/admin";
    }
}
