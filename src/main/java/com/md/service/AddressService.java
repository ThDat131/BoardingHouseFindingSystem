package com.md.service;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;

public interface AddressService {
    Provinces getProvinceById(String id);
    Districts getDistrictById(String id);
    Wards getWardById(String id);
}
