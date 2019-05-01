package ru.nvg.printservice.services;

import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    //ToDo add proper comments
    List<Job> statisticsFilter(User user, JobType type, Device device,
                               LocalDateTime timeFrom, LocalDateTime timeTo);
}
