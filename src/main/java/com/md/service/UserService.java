package com.md.service;

import com.md.pojo.Districts;
import com.md.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    boolean addOrUpdateUser(User user);
    User getUserByUsername(String username);
    boolean isUserExits(String username);
    boolean authUser(String username, String password);

}
