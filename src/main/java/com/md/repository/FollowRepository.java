package com.md.repository;

import com.md.pojo.Follow;

public interface FollowRepository {
    boolean addFollow(Follow follow);
    boolean deleteFollow(String landLordId, String tentantId);
}
