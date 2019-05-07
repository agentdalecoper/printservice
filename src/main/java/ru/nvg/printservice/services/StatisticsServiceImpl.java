package ru.nvg.printservice.services;

import com.querydsl.core.types.OrderSpecifier;
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


    public List<StatisticSummaryNodeDto> toSummary(Iterable<Job> jobs) {
        List<StatisticSummaryNodeDto> res = new ArrayList<>();

        for (Job job : jobs) {
            res.add(new StatisticSummaryNodeDto(job));
        }

        return res;
    }

    @Override
    public List<Job> statisticsFilter(String user, String device, JobType type, LocalDateTime timeFrom,
                                          LocalDateTime timeTo) {
        StatisticFilter filter = new StatisticFilter(user, device,
                type, timeFrom, timeTo);

        StatisticFilterBuilder statisticFilterBuilder = new StatisticFilterBuilder();
        Predicate predicate = statisticFilterBuilder.build(filter);

        return statisticsFilter(predicate);
    }


    private List<Job> statisticsFilter(Predicate predicate) {
        OrderSpecifier<LocalDateTime> order = QJob.job.time.asc();
        return (List<Job>) jobRepository.findAll(predicate, order);
    }
}
