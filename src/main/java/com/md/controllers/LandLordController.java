package com.md.controllers;

import com.md.dto.LandLordUser;
import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.service.LandLordService;
import com.md.service.UserService;
import com.md.validator.LandLordUserValidator;
import com.md.validator.LandLordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class LandLordController {
    @Autowired
    private LandLordService landLordService;
    @Autowired
    private UserService userService;
    @Autowired
    private LandLordUserValidator landLordUserValidator;
    @Autowired
    private LandLordValidator landLordValidator;

    @RequestMapping("/landlords")
    public String users(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("landlords", this.landLordService.getLandLords());
        return "landlords";
    }

    @RequestMapping("/add-landlord")
    public String addLandLord(Model model) {
        model.addAttribute("landlorduser", new LandLordUser());
//        model.addAttribute("landlord", new LandLord());
//        model.addAttribute("user", new User());
        return "add-landlord";
    }

    @PostMapping("/landlord")
    public String addLandLord(Model model , @ModelAttribute(value="landlorduser") LandLordUser lu, BindingResult bindingResult) {
        landLordUserValidator.validate(lu, bindingResult);
        if (!bindingResult.hasErrors()) {
            {
                String username = lu.getUsername();
                String password = lu.getPassword();
                boolean active = lu.isActive();
                String fullName = lu.getFullName();
                String address = lu.getAddress();
                String phone = lu.getPhone();
                String personalId = lu.getPersonalId();
                MultipartFile file = lu.getFile();

                User user = new User(username, password, "", new Date(),active, 1);
                user.setImgUrl(file);
                LandLord landLord = new LandLord(String.valueOf(UUID.randomUUID()), fullName, address, phone, personalId, user);
                if (this.userService.addOrUpdateUser(user)) {
                    if (this.landLordService.addLandLord(landLord)) {
                        return "redirect:/landlords";
                    }
                }
            }
        }

        List<FieldError> errors = bindingResult.getFieldErrors();
        model.addAttribute("errors", errors);
        return "add-landlord";
    }

    @GetMapping("/landlord/{username}")
    public String updateLandLord(Model model, @PathVariable(value="username") String username) {
        model.addAttribute("landlord", this.landLordService.getLandLordByUsername(username));
        return "update-landlord";
    }

    @PostMapping("/landlord-update")
    public String updateLandLord(Model model, @ModelAttribute(value="landlord") LandLord landlord, BindingResult bindingResult) {
        landLordValidator.validate(landlord, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (this.landLordService.updateLandLord(landlord)) {
                return "redirect:/landlords";
            }
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        model.addAttribute("errors", errors);
        return "update-landlord";
    }

    @GetMapping("/landlords-inactivate")
    public String activateLandLordPage(Model model) {
        List<LandLord> landLords = this.landLordService.getLandLordsInactive();
        model.addAttribute("landlords", landLords);
        return "activate-landlord";

    }

}
