package ru.nvg.printservice.services;

import com.querydsl.core.types.Predicate;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.dto.StatisticSummaryNodeDto;

import java.time.LocalDateTime;
import java.util.List;

/*
Service for user statistics, data filtering.
*/

public interface StatisticsService {

    /*
    Method for filtering data based on user search query. All arguments are nullable.
    Should return appropriate list of jobs for a given query.
     */
    List<Job> statisticsFilter(String user, String device, JobType type, LocalDateTime timeFrom,
                               LocalDateTime timeTo);

    /*
    Method for cast to summary list of jobs
     */
    List<StatisticSummaryNodeDto> toSummary(Iterable<Job> jobs);
}
