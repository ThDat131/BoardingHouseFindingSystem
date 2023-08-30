package com.md.repository;

import com.md.pojo.Tentant;
import com.md.pojo.User;

import java.security.Principal;
import java.util.List;

public interface TentantRepository {
    // Xem danh sách người thuê trọ
    List<Tentant> getTentants();
    boolean isUserTentant(String username);
    boolean updateInfoTentant(Principal user, Tentant tentant);

    // Thêm người thuê trọ
    boolean addTentant(Tentant tentant);

    // Lấy người thuê trọ thông qua username
    Tentant getTentantByUsername(String username);
}
