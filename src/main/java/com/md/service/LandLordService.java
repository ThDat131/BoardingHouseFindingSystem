package com.md.service;

import com.md.pojo.LandLord;
import com.md.pojo.User;

import java.util.List;

public interface LandLordService {
    List<LandLord> getLandLords();

    boolean addLandLord(LandLord landLord);
}
