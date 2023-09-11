package com.md.controllers;

import com.md.dto.StatInfo;
import com.md.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiStatsController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/admin/statsNumOfTentant")
    public Map<String, Object> countNumOfTentant(@RequestParam String f, String t ) throws ParseException {
        Map<String, Object> res = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<StatInfo> listTentantByDay = this.statisticService.countNumTentantByDay(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listTentantByMonth = this.statisticService.countNumTentantByMonth(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listTentantByQuarter = this.statisticService.countNumTentantByQuarter(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listTentantByYear = this.statisticService.countNumTentantByYear(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        res.put("statsByDay", listTentantByDay);
        res.put("statsByMonth", listTentantByMonth);
        res.put("statsByQuarter", listTentantByQuarter);
        res.put("statsByYear", listTentantByYear);
        return res;
    }
    @GetMapping("/admin/statsNumOfLandLord")
    public Map<String, Object> countNumOfLandLord(@RequestParam String f, String t ) throws ParseException {
        Map<String, Object> res = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<StatInfo> listLandlordByDay = this.statisticService.countNumLandLordByDay(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listLandLordByMonth = this.statisticService.countNumLandLordByMonth(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listLandLordByQuarter = this.statisticService.countNumLandLordByQuarter(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        List<StatInfo> listLandLordByYear = this.statisticService.countNumLandLordByYear(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        res.put("statsByDay", listLandlordByDay);
        res.put("statsByMonth", listLandLordByMonth);
        res.put("statsByQuarter", listLandLordByQuarter);
        res.put("statsByYear", listLandLordByYear);
        return res;
    }
}
