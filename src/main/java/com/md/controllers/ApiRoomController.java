package com.md.controllers;

import com.md.pojo.Room;
import com.md.pojo.User;
import com.md.service.AddressService;
import com.md.service.RoomService;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private AddressService addressService;


    @PostMapping(path="/room", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Room> addRoom(@RequestBody Map<String, String>params) {
        String address = params.get("address");
        Float price = Float.parseFloat(params.get("price"));
        String provinceId = params.get("provinceId");
        String districtId = params.get("districtId");
        String wardId = params.get("wardId");

        Room room = new Room();
        room.setId(String.valueOf(UUID.randomUUID()));
        room.setAddress(address);
        room.setPrice(price);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        this.roomService.addRoom(room);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @PutMapping(path="/room", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Room> updateRoom(@RequestBody Map<String, String>params) {
        String id = params.get("id");
        String address = params.get("address");
        Float price = Float.parseFloat(params.get("price"));
        String provinceId = params.get("provinceId");
        String districtId = params.get("districtId");
        String wardId = params.get("wardId");

        Room room = new Room();
        room.setId(id);
        room.setAddress(address);
        room.setPrice(price);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        this.roomService.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

}

