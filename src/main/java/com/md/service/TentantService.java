package com.md.service;

import com.md.pojo.Tentant;
import com.md.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface TentantService {
    List<Tentant> getTentants();
    Tentant getTentantByUsername(String username);
    boolean isUserTentant(String username);
    boolean updateInfoTentant(Principal user, Tentant tentant);

    boolean addTentant(Tentant tentant);

    Tentant addTentant(Map<String, String> params);
    boolean addTentantUser(Map<String, String> params, MultipartFile avatar);

}
