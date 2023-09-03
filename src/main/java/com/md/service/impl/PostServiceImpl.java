package com.md.service.impl;

import com.md.advice.ValidationException;
import com.md.pojo.Image;
import com.md.pojo.Post;
import com.md.repository.PostRepository;
import com.md.service.PostService;
import com.md.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostValidator postValidator;
    @Override
    public boolean addLandLordPost(Post post) {
        BindingResult rs = new BeanPropertyBindingResult(post, "post") {
        };
        postValidator.validate(post, rs);
        if (rs.hasErrors()) {
            Map<String, List<String>> errMap = new HashMap<>();
            List<FieldError> errs = rs.getFieldErrors() ;
            for(FieldError err : errs) {
                if (!errMap.containsKey(err.getField()))
                    errMap.put(err.getField(), new ArrayList<>());
                errMap.get(err.getField()).add(err.getCode());
            }
            throw new ValidationException(errMap);
        }
        return postRepository.addLandLordPost(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.getPosts();
    }

    @Override
    public Post getPostById(String id) {
        return postRepository.getPostById(id);
    }
}
