package com.md.validator;

import com.md.pojo.PostOfTenant;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class PostOfTentantValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostOfTenant.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostOfTenant p = (PostOfTenant) target;
        if(p.getAddressToRent().equals(""))
            errors.rejectValue("addressToRent", "post.address.addressNotNull");

    }
}
