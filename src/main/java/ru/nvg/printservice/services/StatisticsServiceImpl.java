package ru.nvg.printservice.services;

import org.springframework.stereotype.Component;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public List<Job> statisticsFilter(User user, JobType type, Device device,
                                      LocalDateTime timeFrom, LocalDateTime timeTo) {
        return null;
    }
}
