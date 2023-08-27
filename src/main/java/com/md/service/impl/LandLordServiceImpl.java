package com.md.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.md.advice.ValidationException;
import com.md.pojo.Image;
import com.md.pojo.LandLord;
import com.md.pojo.User;
import com.md.repository.ImageRepository;
import com.md.repository.LandLordRepository;
import com.md.repository.UserRepository;
import com.md.service.AddressService;
import com.md.service.ImageService;
import com.md.service.LandLordService;
import com.md.service.UserService;
import com.md.validator.LandLordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional(rollbackFor = ValidationException.class)
public class LandLordServiceImpl implements LandLordService {

    @Autowired
    LandLordRepository landLordRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    LandLordValidator landLordValidator;
    @Autowired
    UserService userService;


    @Override
    public List<LandLord> getLandLords() {
        return this.landLordRepository.getLandLords();
    }

    @Override
    public LandLord getLandLordByUsername(String username) {
        return this.landLordRepository.getLandLordByUsername(username);
    }

    @Override
    public boolean isUserLandLord(String username) {
        return this.landLordRepository.isUserLandLord(username);
    }

    @Override
    public boolean updateInfoLandLord(User user, LandLord landLord) {
        return this.landLordRepository.updateInfoLandLord(user, landLord);
    }

    @Override
    @Transactional
    public boolean addLandLord(LandLord landLord) {
        return this.landLordRepository.addLandLord(landLord);
    }

    @Override
    public LandLord addLandLord(Map<String, String> params, List<MultipartFile> files) {
        LandLord landLord = new LandLord();

        String id = String.valueOf(UUID.randomUUID());
        String fullName = params.get("fullName");
        String address = params.get("address");
        String phone = params.get("phone");
        String email = params.get("email");
        String personalId = params.get("personalId");
        String username = params.get("username");

        User userByUsername = this.userRepository.getUserByUsername(username);

        landLord.setId(id);
        landLord.setFullName(fullName);
        landLord.setAddress(address);
        landLord.setPhone(phone);
        landLord.setPersonalId(personalId);
        landLord.setEmail(email);
        landLord.setUsername(userByUsername);

        BindingResult rs = new BeanPropertyBindingResult(landLord, "landLord") {
        };
        landLordValidator.validate(landLord, rs);
        if (rs.hasErrors()) {
            Map<String, List<String>> errMap = new HashMap<>();
            List<FieldError> errs = rs.getFieldErrors() ;
            for(FieldError err : errs) {
                if (!errMap.containsKey(err.getField()))
                    errMap.put(err.getField(), new ArrayList<>());
                errMap.get(err.getField()).add(err.getCode());
            }
            throw new ValidationException(errMap);
        }

        if (this.landLordRepository.addLandLord(landLord)) {
            if(files.size() > 0) {
                for (MultipartFile file : files) {
                    Image i = new Image();
                    i.setId(String.valueOf(UUID.randomUUID()));
                    i.setUrl(file.getName());
                    i.setLandLordId(landLord);
                    i.setFile(file);
                    this.imageService.addImage(i);
                }
            }
        }
        return landLord;
    }

    @Override
    public boolean addLandLordUser(Map<String, String> params, MultipartFile avatar, List<MultipartFile> files) {
        try {
            this.userService.addUser(params, avatar);
            addLandLord(params, files);
            return true;
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getErrors());
        }
    }



}
