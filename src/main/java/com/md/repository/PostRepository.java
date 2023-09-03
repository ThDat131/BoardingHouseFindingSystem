package com.md.repository;

import com.md.pojo.Image;
import com.md.pojo.Post;

import java.util.List;
import java.util.Map;

public interface PostRepository {
    boolean addLandLordPost(Post post);

    List<Post> getPosts();

    Post getPostById(String id);
}
