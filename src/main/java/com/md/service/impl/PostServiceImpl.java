package com.md.service.impl;

import com.md.advice.ValidationException;
import com.md.pojo.Post;
import com.md.pojo.PostOfTenant;
import com.md.pojo.User;
import com.md.repository.PostOfTentantRepository;
import com.md.repository.PostRepository;
import com.md.repository.UserRepository;
import com.md.service.PostService;
import com.md.validator.PostOfTentantValidator;
import com.md.validator.PostTValidator;
import com.md.validator.PostValidator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.util.*;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostValidator postValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostTValidator postTValidator;
    @Autowired
    private PostOfTentantValidator postOfTentantValidator;
    @Autowired
    private PostOfTentantRepository postOfTentantRepository;
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

    @Override
    public Post addTentantPost(Map<String, String> params, Principal user) {
        try {
            Post p = new Post();
            PostOfTenant pt = new PostOfTenant();
            String id = String.valueOf(UUID.randomUUID());
            String name = params.get("name");
            String content = params.get("content");
            Date createdDate = new Date();
            User u = userRepository.getUserByUsername(user.getName());
            String address = params.get("address");

            p.setId(id);
            p.setName(name);
            p.setContent(content);
            p.setCreatedDate(createdDate);
            p.setUsername(u);
            pt.setId(id);
            pt.setAddressToRent(address);

            BindingResult rs = new BeanPropertyBindingResult(p, "post") {
            };
            BindingResult rs1 = new BeanPropertyBindingResult(pt, "postOfTentant") {
            };
            postTValidator.validate(p, rs);
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
            postOfTentantValidator.validate(pt, rs1);
            if (rs1.hasErrors()) {
                Map<String, List<String>> errMap = new HashMap<>();
                List<FieldError> errs = rs1.getFieldErrors() ;
                for(FieldError err : errs) {
                    if (!errMap.containsKey(err.getField()))
                        errMap.put(err.getField(), new ArrayList<>());
                    errMap.get(err.getField()).add(err.getCode());
                }
                throw new ValidationException(errMap);
            }

            if (postRepository.addLandLordPost(p)) {
                if (postOfTentantRepository.addPostOfTentant(pt))
                    return p;
            }
        } catch (HibernateException ex) {
            throw ex;
        }
        return null;
    }

    @Override
    public List<Post> getPostOfTentant() {
        return this.postRepository.getPostOfTentant();
    }

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        return this.postRepository.getPosts(params);
    }

}
