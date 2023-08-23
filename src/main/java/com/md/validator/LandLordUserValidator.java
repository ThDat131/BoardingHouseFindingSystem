package com.md.validator;

import com.md.dto.LandLordUser;
import com.md.pojo.User;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class LandLordUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LandLordUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LandLordUser lu = (LandLordUser) target;

        if (lu.getUsername().isBlank())
            errors.rejectValue("username", "user.username.usernameNotNull");
        if (!lu.getUsername().matches("^.{6,50}$"))
            errors.rejectValue("username", "user.username.usernameLengthError");
        if (lu.getPassword().isBlank())
            errors.rejectValue("password", "user.password.passwordNotNull");
        if (!lu.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$"))
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        if(lu.getFullName().isBlank())
            errors.rejectValue("fullName", "userlandlord.fullName.fullNameNotNull");
        if(lu.getAddress().isBlank()) {
            errors.rejectValue("address", "userlandlord.address.addressNotNull");
        }
        if(lu.getPhone().isBlank()) {
            errors.rejectValue("phone", "userlandlord.phone.phoneNotNull");
        }
        if(!lu.getPhone().matches("^[0-9]{10}$")) {
            errors.rejectValue("phone", "userlandlord.phone.phoneNotAppropriate");
        }
        if(lu.getPersonalId().isBlank()) {
            errors.rejectValue("personalId", "userlandlord.personalId.personalIdNotNull");
        }
        if (!lu.getPersonalId().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("personalId", "userlandlord.personalId.personalIdNotAppropriate");
        }

    }
}
