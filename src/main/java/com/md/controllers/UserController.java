package com.md.controllers;

import com.md.pojo.User;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String users(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.getUsers());
        return "users";
    }

    @RequestMapping("/user-add-or-edit")
    public String userAddOrEdit(Model model) {
        model.addAttribute("user", new User());
        return "user-add-or-edit";
    }

    @PostMapping("/user")
    public String add(@ModelAttribute(value="user") User user) {
        user.setCreatedDate(new Date());
        if (this.userService.addOrUpdateUser(user))
            return "redirect:/";
        return "user-add-or-edit";
    }

    @GetMapping("user/{username}")
    public String update(Model model, @PathVariable(value="username") String username) {
        model.addAttribute("user", this.userService.getUserByUsername(username));
        return "user-add-or-edit";
    }
}
