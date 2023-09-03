package com.md.service.impl;

import com.md.advice.ValidationException;
import com.md.pojo.Room;
import com.md.pojo.User;
import com.md.repository.RoomRepository;
import com.md.service.AddressService;
import com.md.service.RoomService;
import com.md.service.UserService;
import com.md.validator.RoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomValidator roomValidator;

    @Override
    public boolean addRoom(Room room) {
        return this.roomRepository.addRoom(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return this.roomRepository.updateRoom(room);
    }

    @Override
    public List<Room> getRoomsByUsername(String username) {
        return this.roomRepository.getRoomsByUsername(username);
    }

    @Override
    public boolean deleteRoom(String id) {
        return this.roomRepository.deleteRoom(id);
    }

    @Override
    public Room getRoomById(String id) {
        return this.roomRepository.getRoomById(id);
    }

    @Override
    public Room addRoom(Map<String, String> params, Principal user) {
        Room room = new Room();
        Float acreageFloat = 0F;
        Float priceFloat = 0F;
        String address = params.get("address");
        String provinceId = params.get("provinceId");
        String districtId = params.get("districtId");
        String wardId = params.get("wardId");
        String acreage = params.get("acreage");
        String price = params.get("price");
        if (acreage != null && !acreage.equals("") )
            acreageFloat = Float.parseFloat(acreage);
        if (acreage != null && !acreage.equals("") )
            priceFloat = Float.parseFloat(price);
        User u = this.userService.getUserByUsername(user.getName());

        room.setId(String.valueOf(UUID.randomUUID()));
        room.setAddress(address);
        room.setPrice(priceFloat);
        room.setProvinceId(addressService.getProvinceById(provinceId));
        room.setDistrictId(addressService.getDistrictById(districtId));
        room.setWardId(addressService.getWardById(wardId));
        room.setUsername(u);
        room.setAcreage(acreageFloat);

        try {
            BindingResult rs = new BeanPropertyBindingResult(room, "room");
            roomValidator.validate(room, rs);
            if (rs.hasErrors()) {
                Map<String, List<String>> errMap = new HashMap<>();
                List<FieldError> errs = rs.getFieldErrors() ;
                for(FieldError err : errs) {
                    if (!errMap.containsKey(err.getField()))
                        errMap.put(err.getField(), new ArrayList<>());
                    errMap.get(err.getField()).add(err.getCode());
                }
                throw new ValidationException(errMap);
            }
            this.roomRepository.addRoom(room);
            return room;
        }
        catch (ValidationException ex) {
            throw new ValidationException(ex.getErrors());
        }
    }


}
