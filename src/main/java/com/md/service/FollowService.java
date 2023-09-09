package com.md.service;

import com.md.pojo.Follow;
import com.md.pojo.Tentant;

import java.security.Principal;
import java.util.List;
import java.util.Map;


public interface FollowService {
    Follow addFollow(String landLordId, Principal user);
    boolean deleteFollow(String landLordId, String tentantId);
    List<Tentant> getFollowerByLandLordId(String id);
}
