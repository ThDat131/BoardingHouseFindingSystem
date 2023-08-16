package com.md.service.impl;

import com.md.pojo.Room;
import com.md.repository.RoomRepository;
import com.md.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
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
}
