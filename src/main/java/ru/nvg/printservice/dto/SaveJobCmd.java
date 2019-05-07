package ru.nvg.printservice.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SaveJobCmd {
    //Validate here that jobs.length >= 1
    @XmlElement(name = "job") @NotNull
    public List<JobDto> jobs;

    public void addJobDto(JobDto jobDto)
    {
        this.getJobs().add(jobDto);
    }

    public List<JobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDto> jobs) {
        this.jobs = jobs;
    }
}