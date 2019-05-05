package ru.nvg.printservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.dto.JobDto;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.dto.SaveJobCmd;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public JobSummaryDto saveJobs(SaveJobCmd cmd) {
        LocalDateTime time = LocalDateTime.now();

        JobSummaryDto jobSummaryDto = new JobSummaryDto();
        Map<User, Integer> summary = jobSummaryDto.summaryForTransaction;

        for (JobDto jobDto : cmd.jobs) {
            Job job = new Job(jobDto.getId(), jobDto.type, jobDto.user, jobDto.amount, time);
            jobRepository.save(job);
            int amount = summary.getOrDefault(job.user, 0);
            summary.put(job.user, amount + jobDto.amount);
        }

        return jobSummaryDto;
    }
}
