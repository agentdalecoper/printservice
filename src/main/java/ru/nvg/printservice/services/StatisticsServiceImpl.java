package ru.nvg.printservice.services;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.domain.*;
import ru.nvg.printservice.dto.StatisticSummaryNodeDto;
import ru.nvg.printservice.qdsl.StatisticFilter;
import ru.nvg.printservice.qdsl.StatisticFilterBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsServiceImpl implements StatisticsService {
    private final JobRepository jobRepository;

    @Autowired
    public StatisticsServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public Iterable<StatisticSummaryNodeDto> toSummary(Iterable<Job> jobs)
    {
        List<StatisticSummaryNodeDto> res = new ArrayList<>();

        for(Job job: jobs)
        {
            res.add(new StatisticSummaryNodeDto(job));
        }

        return res;
    }

    @Override
    public Iterable<Job> statisticsFilter(String user, String device, JobType type, LocalDateTime timeFrom,
                            LocalDateTime timeTo)
    {
        StatisticFilter filter = new StatisticFilter(user, device,
                type, timeFrom, timeTo);

        StatisticFilterBuilder statisticFilterBuilder = new StatisticFilterBuilder();
        Predicate predicate = statisticFilterBuilder.build(filter);

        return statisticsFilter(predicate);
    }


    private Iterable<Job> statisticsFilter(Predicate predicate) {

        return jobRepository.findAll(predicate);
    }
}
