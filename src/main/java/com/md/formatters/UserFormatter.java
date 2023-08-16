package com.md.formatters;

import com.md.pojo.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserFormatter implements Formatter<User> {
    @Override
    public User parse(String username, Locale locale) throws ParseException {
        return new User(username);
    }

    @Override
    public String print(User user, Locale locale) {
        return user.getUsername();
    }
}
