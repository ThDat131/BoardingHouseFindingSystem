package com.md.repository;

import com.md.pojo.LandLord;
import com.md.pojo.User;

import java.util.List;

public interface LandLordRepository {
    List<LandLord> getLandLords();
    boolean isUserLandLord(String username);
    boolean updateInfoLandLord(User user, LandLord landLord);


    boolean addLandLord(LandLord landLord);

    LandLord getLandLordByUsername(String username);
}
