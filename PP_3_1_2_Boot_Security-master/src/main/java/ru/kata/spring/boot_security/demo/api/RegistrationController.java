package ru.kata.spring.boot_security.demo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.domain.User;
import ru.kata.spring.boot_security.demo.service.UserService;;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(User user) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        userService.savePerson(user);
        return new ModelAndView("login", "user", user);
    }
}
