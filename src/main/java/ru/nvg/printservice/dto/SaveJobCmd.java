package ru.nvg.printservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SaveJobCmd {
    //Validate here that jobs.length >= 1
    @NotNull
    public List<JobDto> jobs;
}