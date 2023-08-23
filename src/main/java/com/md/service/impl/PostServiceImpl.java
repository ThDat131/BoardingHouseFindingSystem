package com.md.service.impl;

import com.md.pojo.Image;
import com.md.pojo.Post;
import com.md.repository.PostRepository;
import com.md.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public boolean addLandLordPost(Post post) {
        return postRepository.addLandLordPost(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.getPosts();
    }
}
