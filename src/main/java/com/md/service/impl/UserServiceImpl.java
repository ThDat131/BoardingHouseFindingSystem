package com.md.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.md.pojo.User;
import com.md.repository.UserRepository;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;

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
}
