package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.domain.Role;
import ru.kata.spring.boot_security.demo.domain.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService personService) {
        return args -> {
            personService.saveRole(new Role(null, "ROLE_USER"));
            personService.saveRole(new Role(null, "ROLE_ADMIN"));

            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            personService.savePerson(user);

            personService.addRoleToPerson(user.getUsername(), "ROLE_ADMIN");
        };
    }
}
