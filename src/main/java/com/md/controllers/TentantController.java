package com.md.controllers;

import com.md.dto.LandLordUser;
import com.md.dto.TentantUser;
import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.service.TentantService;
import com.md.service.UserService;
import com.md.validator.TentantUserValidator;
import com.md.validator.TentantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class TentantController {
    @Autowired
    private TentantService tentantService;
    @Autowired
    private UserService userService;
    @Autowired
    private TentantUserValidator tentantUserValidator;
    @Autowired
    private TentantValidator tentantValidator;

    // Hiển thị danh sách tentant
    @RequestMapping("/tentants")
    public String tentants(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("tentants", this.tentantService.getTentants());
        return "tentants";
    }

    // Hiển thị trang thêm tentant
    @RequestMapping("/add-tentant")
    public String addTentant(Model model) {
        model.addAttribute("tentantuser", new TentantUser());
//        model.addAttribute("landlord", new LandLord());
//        model.addAttribute("user", new User());
        return "add-tentant";
    }

    // Gửi một request tạo mới tentant
    @PostMapping("/tentant")
    public String addTentant(Model model , @ModelAttribute(value="tentantuser") TentantUser tu, BindingResult bindingResult) {
        tentantUserValidator.validate(tu, bindingResult);
        if (!bindingResult.hasErrors()) {
            {
                String username = tu.getUsername();
                String password = tu.getPassword();
                boolean active = tu.isActive();
                String fullName = tu.getFullName();
                String email = tu.getEmail();
                String phone = tu.getPhone();
                String personalId = tu.getPersonalId();
                MultipartFile file = tu.getFile();

                User user = new User(username, password, "", new Date(),active, -1);
                user.setImgUrl(file);
                Tentant tentant= new Tentant(String.valueOf(UUID.randomUUID()), fullName, phone, email, personalId, user);
                if (this.userService.addOrUpdateUser(user)) {
                    if (this.tentantService.addTentant(tentant)) {
                        return "redirect:/tentants";
                    }
                }
            }

        }

        List<FieldError> errors = bindingResult.getFieldErrors();
        model.addAttribute("errors", errors);
        return "add-tentant";
    }

    @RequestMapping("/tentant/{username}")
    public String updateTentant(Model model,
                                @PathVariable(value="username") String username) {
        model.addAttribute("tentant", this.tentantService.getTentantByUsername(username));
        return "update-tentant";
    }

    @PostMapping("/tentant-update")
    public String updateTentant(Model model, @ModelAttribute(value="tentant") Tentant tentant, BindingResult bindingResult) {
        this.tentantValidator.validate(tentant, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (this.tentantService.updateTentant(tentant)) {
                return "redirect:/tentants";
            }
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        model.addAttribute("errors", errors);
        return "update-tentant";
    }
}
