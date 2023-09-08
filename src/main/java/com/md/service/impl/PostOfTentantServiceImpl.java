package com.md.service.impl;

import com.md.pojo.PostOfTenant;
import com.md.repository.PostOfTentantRepository;
import com.md.service.PostOfTentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostOfTentantServiceImpl implements PostOfTentantService {
    @Autowired
    private PostOfTentantRepository postOfTentantRepository;

    @Override
    public List<PostOfTenant> getPostsOfTentant() {
        return postOfTentantRepository.getPostsOfTentant();
    }

    @Override
    public boolean addPostOfTentant(PostOfTenant post) {
        return postOfTentantRepository.addPostOfTentant(post);
    }
}
