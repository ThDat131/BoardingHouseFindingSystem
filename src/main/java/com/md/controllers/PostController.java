package com.md.controllers;

import com.md.dto.TentantUser;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.service.PostService;
import com.md.service.TentantService;
import com.md.service.UserService;
import com.md.validator.TentantUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    // Hiển thị danh sách posts
    @RequestMapping("/posts")
    public String posts(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("posts", this.postService.getPosts());
        return "posts";
    }
}
