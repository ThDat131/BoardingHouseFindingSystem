package com.md.service;

import com.md.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    boolean addOrUpdateUser(User user);
    User getUserByUsername(String username);

}
