package ru.nvg.printservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.User;

import java.util.List;

@RestController
@RequestMapping(StatisticController.BASE_URL)
public class StatisticController {
    public static final String BASE_URL = "/api/v1/statistics";

    @GetMapping
    List<Job> getStatistics(@PathVariable Long sensorId,
                            @RequestParam("user") User user) {
        return null;
    }
}
