package ru.nvg.printservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nvg.printservice.dao.DeviceRepository;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.dao.UserRepository;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.dto.JobDto;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.exceptions.DeviceNotFoundException;
import ru.nvg.printservice.exceptions.UserNotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository, DeviceRepository deviceRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    @Transactional
    public JobSummaryDto saveJobs(SaveJobCmd cmd) {
        LocalDateTime time = LocalDateTime.now();

        JobSummaryDto jobSummaryDto = new JobSummaryDto();
        Map<String, Integer> summary = jobSummaryDto.summaryForTransaction;

        for (JobDto jobDto : cmd.jobs) {
            User user = userRepository.findByName(jobDto.getUserName());

            if (user == null) {
                throw new UserNotFoundException(jobDto.getUserName());
            }

            Device device = deviceRepository.findByName(jobDto.getDeviceName());

            if (device == null) {
                throw new DeviceNotFoundException(jobDto.getDeviceName());
            }

            Job job = new Job(jobDto.getId(), jobDto.getType(), user, device,
                    jobDto.getAmount(), time);

            jobRepository.save(job);
            int amount = summary.getOrDefault(job.user.getName(), 0);
            summary.put(job.user.getName(), amount + jobDto.getAmount());
        }

        return jobSummaryDto;
    }
}
