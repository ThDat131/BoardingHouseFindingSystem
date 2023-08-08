package com.md.service.impl;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;
import com.md.repository.AddressRepository;
import com.md.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Provinces getProvinceById(String id) {
        return addressRepository.getProvinceById(id);
    }

    @Override
    public Districts getDistrictById(String id) {
        return addressRepository.getDistrictById(id);

    }

    @Override
    public Wards getWardById(String id) {
        return addressRepository.getWardById(id);

    }
}
