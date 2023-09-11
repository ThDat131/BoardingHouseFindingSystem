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
    public List<StatInfo> countNumTentantByMonth(Date from, Date to) {
        return statisticRepository.countNumTentantByMonth(from, to);
    }

    @Override
    public List<StatInfo> countNumLandLordByMonth(Date from, Date to) {
        return statisticRepository.countNumLandLordByMonth(from, to);
    }

    @Override
    public List<StatInfo> countNumTentantByDay(Date from, Date to) {
        return statisticRepository.countNumTentantByDay(from, to);
    }

    @Override
    public List<StatInfo> countNumLandLordByDay(Date from, Date to) {
        return statisticRepository.countNumLandLordByDay(from, to);
    }

    @Override
    public List<StatInfo> countNumTentantByQuarter(Date from, Date to) {
        return statisticRepository.countNumTentantByQuarter(from, to);
    }

    @Override
    public List<StatInfo> countNumLandLordByQuarter(Date from, Date to) {
        return statisticRepository.countNumLandLordByQuarter(from, to);
    }

    @Override
    public List<StatInfo> countNumTentantByYear(Date from, Date to) {
        return statisticRepository.countNumTentantByYear(from, to);
    }

    @Override
    public List<StatInfo> countNumLandLordByYear(Date from, Date to) {
        return statisticRepository.countNumLandLordByYear(from, to);
    }
}
