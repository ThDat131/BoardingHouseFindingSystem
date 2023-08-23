package com.md.controllers;

import com.md.pojo.User;
import com.md.service.UserService;
import com.md.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }


    @RequestMapping("/users")
    public String users(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.getUsers());
        return "users";
    }

    @RequestMapping("/add-user")
    public String userAddOrEdit(Model model) {
        model.addAttribute("user", new User());
        return "user-add-or-edit";
    }

    @PostMapping("/user")
    public String add(Model model, @ModelAttribute(value="user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (this.userService.addOrUpdateUser(user))
                return "redirect:/users";
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        model.addAttribute("errors", errors);
        return "user-add-or-edit";
    }

    @GetMapping("/user/{username}")
    public String update(Model model, @PathVariable(value="username") String username) {
        model.addAttribute("user", this.userService.getUserByUsername(username));
        return "user-add-or-edit";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
