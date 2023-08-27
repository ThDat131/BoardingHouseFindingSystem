package com.md.service;

import com.md.pojo.LandLord;
import com.md.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface LandLordService {
    List<LandLord> getLandLords();
    LandLord getLandLordByUsername(String username);
    boolean isUserLandLord(String username);
    boolean updateInfoLandLord(User user, LandLord landLord);


    boolean addLandLord(LandLord landLord);

    LandLord addLandLord(Map<String, String> params, List<MultipartFile> files);

    boolean addLandLordUser(Map<String, String> params, MultipartFile avatar, List<MultipartFile> files);
}
