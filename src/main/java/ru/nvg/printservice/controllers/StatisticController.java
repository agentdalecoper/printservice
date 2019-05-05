package ru.nvg.printservice.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.services.StatisticsService;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(StatisticController.BASE_URL)
public class StatisticController {
    public static final String BASE_URL = "/api/v1/statistics";

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    Iterable<Job> getStatistics(@QuerydslPredicate(root = Job.class) Predicate predicate) {
        return statisticsService.statisticsFilter(predicate);
    }
}
