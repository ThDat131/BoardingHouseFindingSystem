package com.md.repository;

import com.md.pojo.Follow;
import com.md.pojo.Tentant;

import java.util.List;

public interface FollowRepository {
    boolean addFollow(Follow follow);
    boolean deleteFollow(String landLordId, String tentantId);

    List<Tentant> getFollowerByLandLordId(String id);
}
