package com.md.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.md.advice.ValidationException;
import com.md.pojo.LandLord;
import com.md.pojo.User;
import com.md.repository.UserRepository;
import com.md.service.UserService;
import com.md.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("userDetailsService")
@Transactional(rollbackFor = ValidationException.class)
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserValidator userValidator;
    @Autowired
    MessageSource messageSource;

    @Override
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        if (!user.getImgUrl().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getImgUrl().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return this.userRepository.addOrUpdateUser(user);
    }
    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    public boolean isUserExits(String username) {
        return this.userRepository.isUserExits(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return userRepository.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar){
        User user = new User();
        String username = params.get("username");
        String password = params.get("password");
        boolean isActive = Boolean.parseBoolean(params.get("active"));
        int role = Integer.parseInt(params.get("role"));

        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedDate(new Date());
        user.setIsActive(isActive);
        user.setRole(role);

        BindingResult rs = new BeanPropertyBindingResult(user, "user") {
        };
        userValidator.validate(user, rs);
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

        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        user.setPassword(this.passwordEncoder.encode(password));
        this.userRepository.addUser(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepository.getUserByUsername(username);
        if (u == null)
            throw new UsernameNotFoundException("Invalid");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }
}
