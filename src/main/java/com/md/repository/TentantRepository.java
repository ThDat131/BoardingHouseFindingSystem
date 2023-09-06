package com.md.repository;

import com.md.dto.StatInfo;
import com.md.pojo.Tentant;
import com.md.pojo.User;

import java.security.Principal;
import java.util.Date;
import java.util.List;

public interface TentantRepository {
    // Xem danh sách người thuê trọ
    List<Tentant> getTentants();

    // Kiểm tra vai trò
    boolean isUserTentant(String username);

    // Thay đổi thông tin người thuê trọ
    boolean updateInfoTentant(Principal user, Tentant tentant);

    // Thêm người thuê trọ
    boolean addTentant(Tentant tentant);

    // Lấy người thuê trọ thông qua username
    Tentant getTentantByUsername(String username);

    boolean updateTentant(Tentant tentant);

    // Lấy tổng số người thuê trọ theo mốc thời gian

}
