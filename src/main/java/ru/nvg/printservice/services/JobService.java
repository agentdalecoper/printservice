package ru.nvg.printservice.services;

import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.dto.JobSummaryDto;


public interface JobService {
    JobSummaryDto saveJobs(SaveJobCmd cmd);
}
