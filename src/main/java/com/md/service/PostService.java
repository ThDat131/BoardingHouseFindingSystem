package com.md.service;

import com.md.pojo.Image;
import com.md.pojo.Post;

import java.util.List;
import java.util.Map;

public interface PostService {
    boolean addLandLordPost(Post post);
    List<Post> getPosts();

}
