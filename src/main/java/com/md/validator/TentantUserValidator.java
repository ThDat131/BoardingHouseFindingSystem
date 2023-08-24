package com.md.validator;

import com.md.dto.LandLordUser;
import com.md.dto.TentantUser;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class TentantUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TentantUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TentantUser tu = (TentantUser) target;

        if (tu.getUsername().isBlank())
            errors.rejectValue("username", "user.username.usernameNotNull");
        if (!tu.getUsername().matches("^.{6,50}$"))
            errors.rejectValue("username", "user.username.usernameLengthError");
        if (tu.getPassword().isBlank())
            errors.rejectValue("password", "user.password.passwordNotNull");
        if (!tu.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$"))
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        if (tu.getRePassword().isBlank())
            errors.rejectValue("rePassword", "user.rePassword.rePasswordNotNull");
        if (!tu.getRePassword().matches(tu.getPassword()))
            errors.rejectValue("rePassword", "user.rePassword.rePasswordIsNotMatch");
        if(tu.getFullName().isBlank())
            errors.rejectValue("fullName", "usertentant.fullName.fullNameNotNull");
        if(tu.getEmail().isBlank()) {
            errors.rejectValue("email", "usertentant.email.emailNotNull");
        }
        if(tu.getPhone().isBlank()) {
            errors.rejectValue("phone", "usertentant.phone.phoneNotNull");
        }
        if(!tu.getPhone().matches("^[0-9]{10}$")) {
            errors.rejectValue("phone", "usertentant.phone.phoneNotAppropriate");
        }
        if(tu.getPersonalId().isBlank()) {
            errors.rejectValue("personalId", "usertentant.personalId.personalIdNotNull");
        }
        if (!tu.getPersonalId().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("personalId", "usertentant.personalId.personalIdNotAppropriate");
        }
    }
}
