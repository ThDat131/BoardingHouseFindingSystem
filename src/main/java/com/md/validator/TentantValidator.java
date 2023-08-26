package com.md.validator;

import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.service.TentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class TentantValidator implements Validator {
    @Autowired
    TentantService tentantService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Tentant.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tentant tentant = (Tentant) target;

        if (tentant.getFullName().equals(""))
            errors.rejectValue("fullName", "tentant.fullName.fullNameNotNull");
        if (tentant.getPhone().equals(""))
            errors.rejectValue("phone", "tentant.phone.phoneNotNull");
        if (tentant.getEmail().equals(""))
            errors.rejectValue("email", "tentant.email.emailNotNull");
        if(tentant.getPersonalId().equals(""))
            errors.rejectValue("personalId", "tentant.personalId.personalIdNotNull");

    }
}
