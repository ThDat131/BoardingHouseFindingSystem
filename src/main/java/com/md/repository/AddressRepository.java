package com.md.repository;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;

import java.util.List;

public interface AddressRepository {
    Provinces getProvinceById(String id);
    Districts getDistrictById(String id);
    Wards getWardById(String id);
    List<Provinces> getProvinces();
    List<Districts> getDistrictByProvincesCode(String code);
    List<Wards> getWardsByDistrictCode(String code);
}
