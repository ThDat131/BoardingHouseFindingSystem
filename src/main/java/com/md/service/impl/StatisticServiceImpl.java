package com.md.service.impl;

import com.md.dto.StatInfo;
import com.md.repository.StatisticRepository;
import com.md.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;
    @Override
    public List<StatInfo> countNumTentantByDate(Date from, Date to) {
        return statisticRepository.countNumTentantByDate(from, to);
    }

    @Override
    public List<StatInfo> countNumLandLordByDate(Date from, Date to) {
        return statisticRepository.countNumLandLordByDate(from, to);
    }
}
