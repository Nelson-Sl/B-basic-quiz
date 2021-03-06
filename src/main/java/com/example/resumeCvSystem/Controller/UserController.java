package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Service.UserService;
import com.example.resumeCvSystem.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid User user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        // GTB: - 没有处理 user 为 null 的场景？
        return this.userService.getUserById(id);
    }
}
