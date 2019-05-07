package ru.nvg.printservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.dto.StatisticSummaryNodeDto;
import ru.nvg.printservice.services.StatisticsService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(StatisticController.BASE_URL)
@Slf4j
public class StatisticController {
    public static final String BASE_URL = "/api/v1/statistics";

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    List<StatisticSummaryNodeDto> getStatistics(@RequestParam(required=false) String user,
                                @RequestParam(required=false) String device,
                                @RequestParam(required=false) JobType type,
                                @RequestParam(required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
                                @RequestParam(required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo) {
        log.debug("Querying for statistics, cmd - user {} device {} type {} timefrom {} timeto {}",
                user, device, type, timeFrom, timeTo);

        List<Job> jobs =  statisticsService.statisticsFilter(user, device, type, timeFrom, timeTo);

        return statisticsService.toSummary(jobs);
    }
}
