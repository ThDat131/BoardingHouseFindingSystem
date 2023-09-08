package com.md.service;

import com.md.pojo.Image;
import com.md.pojo.Post;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface PostService {
    boolean addLandLordPost(Post post);
    List<Post> getPosts();
    Post getPostById(String id);
    Post addTentantPost(Map<String, String> params, Principal user);
    List<Post> getPostOfTentant();
    List<Post> getPosts(Map<String, String> params);
}
