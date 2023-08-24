package com.md.repository;

import com.md.pojo.Tentant;

import java.util.List;

public interface TentantRepository {
    // Xem danh sách người thuê trọ
    List<Tentant> getTentants();

    // Thêm người thuê trọ
    boolean addTentant(Tentant tentant);
}
