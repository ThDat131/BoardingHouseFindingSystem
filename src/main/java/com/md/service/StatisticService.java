package com.md.service;

import com.md.dto.StatInfo;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<StatInfo> countNumTentantByMonth(Date from, Date to);
    List<StatInfo> countNumLandLordByMonth(Date from, Date to);
    List<StatInfo> countNumTentantByDay(Date from, Date to);
    List<StatInfo> countNumLandLordByDay(Date from, Date to);
    List<StatInfo> countNumTentantByQuarter(Date from, Date to);
    List<StatInfo> countNumLandLordByQuarter(Date from, Date to);
    List<StatInfo> countNumTentantByYear(Date from, Date to);
    List<StatInfo> countNumLandLordByYear(Date from, Date to);
}
