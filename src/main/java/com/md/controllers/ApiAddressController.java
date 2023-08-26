package com.md.controllers;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;
import com.md.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiAddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/provinces/")
    public ResponseEntity<List<Provinces>> getProvinces(){
        List<Provinces> provinces = this.addressService.getProvinces();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }
    @GetMapping("/province/{code}/districts/")
    public ResponseEntity<List<Districts>> getDistrictByProvincesCode(@PathVariable(value ="code") String code){ // value tren param link
        List<Districts> districts = this.addressService.getDistrictByProvincesCode(code);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }
    @GetMapping("/district/{code}/wards/")
    public ResponseEntity<List<Wards>> getWardsByDistrictCode(@PathVariable(value ="code") String code){ // value tren param link
        List<Wards> wards = this.addressService.getWardsByDistrictCode(code);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

}
