package com.md.service.impl;

import com.md.pojo.Follow;
import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
import com.md.repository.FollowRepository;
import com.md.repository.LandLordRepository;
import com.md.repository.UserRepository;
import com.md.service.FollowService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LandLordRepository landLordRepository;
    @Override
    public Follow addFollow(String landLordId, Principal user) {
        try {
            Follow follow = new Follow();
            String username = user.getName();
            Tentant tentant = userRepository.getUserByUsername(username).getTentant();
            LandLord landLord = landLordRepository.getLandLordById(landLordId);
            follow.setId(String.valueOf(UUID.randomUUID()));
            follow.setLandLordId(landLord);
            follow.setTenantId(tentant);
            this.followRepository.addFollow(follow);
            return follow;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deleteFollow(String landLordId, String tentantId) {
        return followRepository.deleteFollow(landLordId,tentantId);
    }

    @Override
    public List<Tentant> getFollowerByLandLordId(String id) {
        return followRepository.getFollowerByLandLordId(id);
    }
}
