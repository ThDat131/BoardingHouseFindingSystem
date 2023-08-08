package com.md.repository;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;

public interface AddressRepository {
    Provinces getProvinceById(String id);
    Districts getDistrictById(String id);
    Wards getWardById(String id);
}
