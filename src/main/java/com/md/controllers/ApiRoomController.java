package com.md.controllers;

import com.md.advice.ValidationException;
import com.md.pojo.Room;
import com.md.pojo.User;
import com.md.service.AddressService;
import com.md.service.RoomService;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

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
    @Autowired
    private MessageSource messageSource;

    @PostMapping(path="/room/", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addRoom(@RequestBody Map<String, String>params, Principal user) {
        try {
            Room room = this.roomService.addRoom(params, user);
            if (room != null)
                return new ResponseEntity(room, HttpStatus.CREATED);
        } catch (ValidationException ve) {
            Map<String, List<String>> errors = ve.getErrors();
            Map<String, List<String>> errorsRes = new HashMap<>();
            errors.forEach((key, value) -> {
                List<String> listErr = new ArrayList<>();
                for(String errCode: value)
                    listErr.add(messageSource.getMessage(errCode, null, Locale.getDefault()));
                errorsRes.put(key, listErr);
            });
            return new ResponseEntity(errorsRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path="/room/", produces = {
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        Room room = new Room();
        room.setId(id);
        room.setAddress(address);
        room.setPrice(price);
        room.setAcreage(acreage);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        room.setUsername(userService.getUserByUsername(auth.getName()));
        this.roomService.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/rooms/")
    public ResponseEntity<List<Room>> getRoomsByUsername(Principal user) {
        List<Room> rooms = this.roomService.getRoomsByUsername(user.getName());
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @DeleteMapping({"/room/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable(value = "id") String id) {
        this.roomService.deleteRoom(id);
    }

    @GetMapping("/room/{id}/")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") String id) {
        Room room = this.roomService.getRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}

