package com.md.controllers;

import com.md.components.JwtService;
import com.md.dto.LandLordUser;
import com.md.dto.TentantUser;
import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
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

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPersonalController {
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

    @GetMapping(path = "/tentant/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tentant> tentantDetails(Principal user) {
        if (tentantService.isUserTentant(user.getName())) {
            Tentant t = this.tentantService.getTentantByUsername(user.getName());
            return new ResponseEntity<>(t, HttpStatus.OK);
        }

        return new ResponseEntity<>(new Tentant(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/landlord/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandLord> landLordDetails(Principal user) {
        if (landLordService.isUserLandLord(user.getName())) {
            LandLord l = this.landLordService.getLandLordByUsername(user.getName());
            return new ResponseEntity<>(l, HttpStatus.OK);
        }

        return new ResponseEntity<>(new LandLord(), HttpStatus.BAD_REQUEST);
    }
}
