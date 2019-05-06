package ru.nvg.printservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}