package com.md.service;

import com.md.pojo.Room;

import java.util.List;

public interface RoomService {
    boolean addRoom(Room room);
    boolean updateRoom(Room room);
    List<Room> getRoomsByUsername(String username);
    boolean deleteRoom(String id);
    Room getRoomById(String id);
}
