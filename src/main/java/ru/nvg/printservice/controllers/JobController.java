package ru.nvg.printservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.services.JobService;

import java.awt.*;

@RestController
@RequestMapping(JobController.BASE_URL)
@Slf4j
public class JobController {
    public static final String BASE_URL = "/api/v1/jobs";

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public JobSummaryDto createJob(@RequestBody SaveJobCmd cmd) {
        log.debug("creating job " + cmd);

        return jobService.saveJobs(cmd);
    }
}
