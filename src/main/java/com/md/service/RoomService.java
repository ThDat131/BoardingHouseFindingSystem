package com.md.service;

import com.md.pojo.Room;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface RoomService {
    boolean addRoom(Room room);
    boolean updateRoom(Room room);
    List<Room> getRoomsByUsername(String username);
    boolean deleteRoom(String id);
    Room getRoomById(String id);
    Room addRoom(Map <String, String> params, Principal user);
}
