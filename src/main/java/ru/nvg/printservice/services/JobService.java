package ru.nvg.printservice.services;

import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.dto.JobSummaryDto;

/*
Service for working with jobs
 */
public interface JobService {
    /*
 Method persists jobs in database, returns summary for added job.

- job - job information;
jobDto:
- type - job type @see JobType;
- user - user name;
- device - device name/address;
- amount - amount of work
     */
    JobSummaryDto saveJobs(SaveJobCmd cmd);
}
