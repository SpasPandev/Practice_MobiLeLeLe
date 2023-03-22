package com.example.practice_mobilelele.web;

import com.example.practice_mobilelele.model.binding.UserLoginBindinModel;
import com.example.practice_mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindinModel userLoginBindinModel) {

        userService.login(userLoginBindinModel.getUsername(), userLoginBindinModel.getPassword());

        return "redirect:/";
    }
}
