package com.example.practice_mobilelele.web;

import com.example.practice_mobilelele.model.binding.UserLoginBindinModel;
import com.example.practice_mobilelele.model.binding.UserRegisterBindingModel;
import com.example.practice_mobilelele.service.UserService;
import com.example.practice_mobilelele.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/users/login")
    public String login() {

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindinModel userLoginBindinModel, Model model) {

        boolean loginSuccessful = userService.login(userLoginBindinModel.getUsername(), userLoginBindinModel.getPassword());

        if (!loginSuccessful) {
            return "auth-login";
        }

        model.addAttribute("currentUser", currentUser);


        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout() {

        userService.logout();

        return "redirect:/";
    }

    @GetMapping("/users/register")
    public String register() {

        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(userRegisterBindingModel);

        return "redirect:/users/login";
    }
}
