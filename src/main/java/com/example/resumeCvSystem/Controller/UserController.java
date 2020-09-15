package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Service.UserService;
import com.example.resumeCvSystem.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }
}
