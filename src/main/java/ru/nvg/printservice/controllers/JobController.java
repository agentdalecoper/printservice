package ru.nvg.printservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nvg.printservice.dto.JobDto;
import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.services.JobService;

import java.util.ArrayList;

@Controller
@RequestMapping(JobController.BASE_URL)
@Slf4j
public class JobController {
    public static final String BASE_URL = "/api/v1/jobs";

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping
    public String jobForm(Model model) {
        SaveJobCmd cmd = new SaveJobCmd();
        cmd.setJobs(new ArrayList<>());
        cmd.addJobDto(new JobDto());

        model.addAttribute("job", new JobDto());
        return "jobform";
    }

    @PostMapping
    @ResponseBody
    public JobSummaryDto saveJobBulk(@ModelAttribute @RequestBody @Validated SaveJobCmd saveJobCmd) {
        log.debug("saving jobs" + saveJobCmd);

        return jobService.saveJobs(saveJobCmd);
    }

    @PostMapping(value = "/one")
    @ResponseBody
    public JobSummaryDto saveOneJob(@ModelAttribute @RequestBody @Validated JobDto job) {
        log.debug("saving one job " + job);
        SaveJobCmd saveJobCmd = new SaveJobCmd();
        saveJobCmd.jobs = new ArrayList<>();
        saveJobCmd.jobs.add(job);

        return jobService.saveJobs(saveJobCmd);
    }
}
