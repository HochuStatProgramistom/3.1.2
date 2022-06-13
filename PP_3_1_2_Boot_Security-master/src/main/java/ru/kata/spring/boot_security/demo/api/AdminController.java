package ru.kata.spring.boot_security.demo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.domain.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/api/admin")
    public String getUser(Model model, Principal principal) {
        User user = userService.getPerson(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping(value = "/api/admin/users")
    public String findAll(Model model) {
        List<User> users = userService.getPersons();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/api/admin/user-create")
    public String createUserForm(User user) {
        return "new";
    }

    @PostMapping("/api/admin/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.savePerson(user);
        return "redirect:/api/admin/users";
    }

    @GetMapping(value = "/api/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/api/admin/users";
    }

    @GetMapping(value = "/api/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/api/admin/user-update")
    public String updateUser(User user) {
        userService.savePerson(user);
        return "redirect:/api/admin/users";
    }
}
