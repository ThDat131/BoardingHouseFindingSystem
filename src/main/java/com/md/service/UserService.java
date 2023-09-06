package com.md.service;

import com.md.pojo.Districts;
import com.md.pojo.LandLord;
import com.md.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    boolean addOrUpdateUser(User user);
    User getUserByUsername(String username);
    boolean isUserExits(String username);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);
    boolean activateUser(String username);
}
