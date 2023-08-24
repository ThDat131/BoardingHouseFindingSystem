package com.md.service.impl;

import com.md.pojo.Tentant;
import com.md.repository.TentantRepository;
import com.md.repository.UserRepository;
import com.md.service.TentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TentantServiceImpl implements TentantService {
    @Autowired
    TentantRepository tentantRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Tentant> getTentants() {
        return this.tentantRepository.getTentants();
    }

    @Override
    public boolean addTentant(Tentant tentant) {
        return this.tentantRepository.addTentant(tentant);
    }
}
