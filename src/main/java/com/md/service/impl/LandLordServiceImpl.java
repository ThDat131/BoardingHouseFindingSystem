package com.md.service.impl;

import com.md.pojo.LandLord;
import com.md.pojo.User;
import com.md.repository.LandLordRepository;
import com.md.repository.UserRepository;
import com.md.service.AddressService;
import com.md.service.LandLordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LandLordServiceImpl implements LandLordService {

    @Autowired
    LandLordRepository landLordRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<LandLord> getLandLords() {
        return this.landLordRepository.getLandLords();
    }

    @Override
    @Transactional
    public boolean addLandLord(LandLord landLord) {
        return this.landLordRepository.addLandLord(landLord);
    }


}
