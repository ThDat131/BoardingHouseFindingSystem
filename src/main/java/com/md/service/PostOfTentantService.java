package com.md.service;

import com.md.pojo.PostOfTenant;

import java.util.List;

public interface PostOfTentantService {
    List<PostOfTenant> getPostsOfTentant();
    boolean addPostOfTentant(PostOfTenant post);
}
