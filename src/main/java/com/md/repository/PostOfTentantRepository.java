package com.md.repository;

import com.md.pojo.PostOfTenant;

import java.util.List;

public interface PostOfTentantRepository {
    List<PostOfTenant> getPostsOfTentant();
    boolean addPostOfTentant(PostOfTenant post);
}
