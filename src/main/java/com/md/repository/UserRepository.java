package com.md.repository;

import com.md.pojo.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    boolean addOrUpdateUser(User user);
    User getUserByUsername(String username);
    boolean isUserExits(String username);
}
