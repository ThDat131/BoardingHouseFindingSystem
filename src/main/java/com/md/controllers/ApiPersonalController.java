package com.md.controllers;

import com.md.components.JwtService;
import com.md.dto.LandLordUser;
import com.md.dto.TentantUser;
import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.repository.LandLordRepository;
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
import java.util.Map;

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

    @GetMapping(path = "/tentant/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tentant> tentantDetails(Principal user) {
        Tentant t = this.tentantService.getTentantByUsername(user.getName());

        if (t != null) {
            return new ResponseEntity<>(t, HttpStatus.OK);
        }

        return new ResponseEntity<>(new Tentant(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/landlord/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandLord> landLordDetails(Principal user) {
        LandLord l = this.landLordService.getLandLordByUsername(user.getName());

        if (l != null) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        }

        return new ResponseEntity<>(l, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/tentant-details/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeTentantDetails(Principal user, @RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        String name = params.get("name");
        String email = params.get("email");
        String phone = params.get("phone");

        Tentant t = new Tentant();
        t.setEmail(email);
        t.setPhone(phone);
        t.setFullName(name);

        User u = this.userService.getUserByUsername(user.getName());
        u.setImgUrl(avatar);
        Boolean isChangeUser = userService.addOrUpdateUser(u);
        Boolean isChangeSuccess = tentantService.updateInfoTentant(user, t);
    }

    @PutMapping(path = "/landlord-details/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeLandLordDetails(Principal user, Map<String, String>params) {
        String name = params.get("name");
        String imgPath = params.get("imgPath");
        String email = params.get("email");
        String phone = params.get("phone");
        String address = params.get("address");

        LandLord l = new LandLord();
        l.setEmail(email);
        l.setPhone(phone);
        l.setFullName(name);
        l.setAddress(address);

        User u = new User();
        u.setAvatar(imgPath);

        Boolean isChangeUser = userService.addOrUpdateUser(u);
        Boolean isChangeSuccess = landLordService.updateInfoLandLord(user, l);
    }
}
