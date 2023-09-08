package com.md.service;

import com.md.dto.StatInfo;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<StatInfo> countNumTentantByDate(Date from, Date to);
    List<StatInfo> countNumLandLordByDate(Date from, Date to);

}
