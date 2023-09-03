package com.md.validator;

import com.md.pojo.Post;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@PropertySource("classpath:messages.properties")
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Post.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;

        if (post.getName().equals(""))
            errors.rejectValue("name", "post.name.nameNotNull");
        if (post.getContent().equals(""))
            errors.rejectValue("content", "post.content.contentNotNull");
        if (post.getRoomId() == null)
            errors.rejectValue("roomId", "post.roomId.roomIdNotNull");

    }
}
