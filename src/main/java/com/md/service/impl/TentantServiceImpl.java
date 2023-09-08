package com.md.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.md.advice.ValidationException;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.repository.TentantRepository;
import com.md.repository.UserRepository;
import com.md.service.TentantService;
import com.md.service.UserService;
import com.md.validator.TentantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
@Transactional(rollbackFor = ValidationException.class)
public class TentantServiceImpl implements TentantService {
    @Autowired
    TentantRepository tentantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TentantValidator tentantValidator;
    @Autowired
    UserService userService;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Tentant> getTentants() {
        return this.tentantRepository.getTentants();
    }

    @Override
    public Tentant getTentantByUsername(String username) {
        return this.tentantRepository.getTentantByUsername(username);
    }

    @Override
    public boolean isUserTentant(String username) {
        return this.tentantRepository.isUserTentant(username);
    }

    @Override
    public boolean updateInfoTentant(Principal user, Tentant tentant) {
        return this.tentantRepository.updateInfoTentant(user, tentant);
    }

    @Override
    public boolean updateTentant(Tentant tentant) {
        return this.tentantRepository.updateTentant(tentant);
    }

    @Override
    public boolean addTentant(Tentant tentant) {
        return this.tentantRepository.addTentant(tentant);
    }

    @Override
    public List<Tentant> getTentantsInactive() {
        return this.tentantRepository.getTentantsInactive();
    }

    @Override
    public Tentant addTentant(Map<String, String> params) {
        Tentant tentant = new Tentant();

        String id = String.valueOf(UUID.randomUUID());
        String fullName = params.get("fullName");
        String phone = params.get("phone");
        String personalId = params.get("personalId");
        String email = params.get("email");
        String username = params.get("username");
        User userByUsername = this.userRepository.getUserByUsername(username);

        tentant.setId(id);
        tentant.setFullName(fullName);
        tentant.setPhone(phone);
        tentant.setPersonalId(personalId);
        tentant.setEmail(email);
        tentant.setUsername(userByUsername);

        BindingResult rs = new BeanPropertyBindingResult(tentant, "tentant") {
        };
        tentantValidator.validate(tentant, rs);
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
        this.tentantRepository.addTentant(tentant);
        return tentant;
    }
    @Override
    public boolean addTentantUser(Map<String, String> params, MultipartFile avatar) {
        try {
            this.userService.addUser(params, avatar);
            addTentant(params);
            return true;
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getErrors());
        }
    }
}
