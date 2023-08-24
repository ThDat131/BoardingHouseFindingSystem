package com.md.service;

import com.md.pojo.Tentant;

import java.util.List;

public interface TentantService {
    List<Tentant> getTentants();

    boolean addTentant(Tentant tentant);
}
