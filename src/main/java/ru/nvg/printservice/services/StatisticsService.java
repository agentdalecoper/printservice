package ru.nvg.printservice.services;

import com.querydsl.core.types.Predicate;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    //ToDo add proper comments
    Iterable<Job> statisticsFilter(String user, String device, JobType type, LocalDateTime timeFrom,
                                   LocalDateTime timeTo);
}
