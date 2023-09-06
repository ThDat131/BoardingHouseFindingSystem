package com.md.validator;

import com.md.pojo.User;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class UserUpdateValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getUsername().equals(""))
            errors.rejectValue("username", "user.username.usernameNotNull");
        if (!user.getUsername().matches("^.{6,50}$"))
            errors.rejectValue("username", "user.username.usernameLengthError");
        if (user.getPassword().equals(""))
            errors.rejectValue("password", "user.password.passwordNotNull");
        if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$"))
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        if (user.getRole() != -1 && user.getRole() != 0 && user.getRole() != 1) {
            errors.rejectValue("role", "user.role.roleNotAppropriate");
        }
    }
}
