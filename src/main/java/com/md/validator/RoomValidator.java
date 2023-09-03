package com.md.validator;

import com.md.pojo.Room;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class RoomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Room.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Room room = (Room) target;

        if (room.getAddress().equals(""))
            errors.rejectValue("address", "room.address.addressNotNull");
        if (room.getPrice() <= 0)
            errors.rejectValue("price", "room.price.priceNotAppropriate");
        if (String.valueOf(room.getPrice()).equals("")) {
            errors.rejectValue("price", "room.price.priceNotNull");
        }
        if (room.getAcreage() <= 0)
            errors.rejectValue("acreage", "room.acreage.acreageNotAppropriate");
    }
}
