package com.md.controllers;

import com.md.advice.ValidationException;
import com.md.components.JwtService;
import com.md.pojo.User;
import com.md.service.LandLordService;
import com.md.service.TentantService;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LandLordService landLordService;
    @Autowired
    private TentantService tentantService;
    @Autowired
    MessageSource messageSource;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword())) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> details(Principal user) {
            User u = this.userService.getUserByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    @PostMapping(path = "/landlord-user/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity addLandLord(@RequestParam Map<String, String> params,
                                                    @RequestPart MultipartFile avatar,
                                                    @RequestParam List<MultipartFile> images) {
        try {
            if (this.landLordService.addLandLordUser(params, avatar, images))
                return new ResponseEntity("Success", HttpStatus.CREATED);
        } catch (ValidationException ve) {
            Map<String, List<String>> errors = ve.getErrors();
            Map<String, List<String>> errorsRes = new HashMap<>();
            errors.forEach((key, value) -> {
                List<String> listErr = new ArrayList<>();
                for(String errCode: value)
                    listErr.add(messageSource.getMessage(errCode, null, Locale.getDefault()));
                errorsRes.put(key, listErr);
            });
            return new ResponseEntity(errorsRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

    }

    @PostMapping(path = "/tentant-user/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity addTentant(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        try {
            if (tentantService.addTentantUser(params, avatar))  {
                return new ResponseEntity("Success", HttpStatus.CREATED);
            }
        } catch (ValidationException ve) {
            Map<String, List<String>> errors = ve.getErrors();
            Map<String, List<String>> errorsRes = new HashMap<>();
            errors.forEach((key, value) -> {
                List<String> listErr = new ArrayList<>();
                for(String errCode: value)
                    listErr.add(messageSource.getMessage(errCode, null, Locale.getDefault()));
                errorsRes.put(key, listErr);
            });
            return new ResponseEntity(errorsRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
