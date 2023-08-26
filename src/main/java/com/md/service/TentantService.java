package com.md.service;

import com.md.pojo.Tentant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TentantService {
    List<Tentant> getTentants();

    boolean addTentant(Tentant tentant);

    Tentant addTentant(Map<String, String> params);
    boolean addTentantUser(Map<String, String> params, MultipartFile avatar);

}
