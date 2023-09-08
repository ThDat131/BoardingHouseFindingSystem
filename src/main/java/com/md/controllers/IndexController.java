package com.md.controllers;

import com.md.dto.StatInfo;
import com.md.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private StatisticService statisticService;
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
