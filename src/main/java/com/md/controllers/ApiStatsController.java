package com.md.controllers;

import com.md.dto.StatInfo;
import com.md.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiStatsController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/admin/statsNumOfTentant")
    public ResponseEntity countNumOfTentant(@RequestParam String f, String t ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<StatInfo> list = this.statisticService.countNumTentantByDate(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/admin/statsNumOfLandLord")
    public ResponseEntity countNumOfLandLord(@RequestParam String f, String t ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<StatInfo> list = this.statisticService.countNumLandLordByDate(simpleDateFormat.parse(f),simpleDateFormat.parse(t));
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
