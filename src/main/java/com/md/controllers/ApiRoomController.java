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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private  UserService userService;


    @PostMapping(path="/room", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Room> addRoom(@RequestBody Map<String, String>params) {
        String address = params.get("address");
        Float price = Float.parseFloat(params.get("price"));
        String provinceId = params.get("provinceId");
        String districtId = params.get("districtId");
        String wardId = params.get("wardId");
        String username = params.get("username");
        Float acreage = Float.parseFloat(params.get("acreage"));

        Room room = new Room();
        room.setId(String.valueOf(UUID.randomUUID()));
        room.setAddress(address);
        room.setPrice(price);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        room.setUsername(userService.getUserByUsername(username));
        room.setAcreage(acreage);
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
        Float acreage = Float.parseFloat(params.get("acreage"));
        String username = params.get("username");

        Room room = new Room();
        room.setId(id);
        room.setAddress(address);
        room.setPrice(price);
        room.setAcreage(acreage);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        room.setUsername(userService.getUserByUsername(username));
        this.roomService.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/{username}/rooms")
    public ResponseEntity<List<Room>> getRoomsByUsername(@PathVariable(value = "username") String username) {
        List<Room> rooms = this.roomService.getRoomsByUsername(username);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @DeleteMapping({"/room/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable(value = "id") String id) {
        this.roomService.deleteRoom(id);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") String id) {
        Room room = this.roomService.getRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}

