package com.md.validator;

import com.md.pojo.LandLord;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class LandLordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LandLord.class.isAssignableFrom(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        LandLord landLord = (LandLord) target;
        if (landLord.getFullName().equals(""))
            errors.rejectValue("fullName", "landLord.fullName.fullNameNotNull");
        if (landLord.getPhone().equals(""))
            errors.rejectValue("phone", "landLord.phone.phoneNotNull");
        if (landLord.getEmail().equals(""))
            errors.rejectValue("email", "landLord.email.emailNotNull");
        if(landLord.getPersonalId().equals(""))
            errors.rejectValue("personalId", "landLord.personalId.personalIdNotNull");
        if(landLord.getAddress().equals(""))
            errors.rejectValue("address", "landLord.address.addressNotNull");
    }
}
