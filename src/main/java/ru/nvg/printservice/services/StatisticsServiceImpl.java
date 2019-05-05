package ru.nvg.printservice.services;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Component;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class StatisticsServiceImpl implements StatisticsService {
    private final JobRepository jobRepository;

    @Autowired
    public StatisticsServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Iterable<Job> statisticsFilter(Predicate predicate) {
        return jobRepository.findAll(predicate);
    }
}
