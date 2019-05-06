package ru.nvg.printservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nvg.printservice.domain.Job;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticSummaryNodeDto {
    Long JobId;
    String user;
    String device;
    Integer amount;
    LocalDateTime time;

    public StatisticSummaryNodeDto(Job job)
    {
        JobId = job.getId();
        user = job.user.getName();
        device = job.device.getName();
        amount = job.amount;
        time = job.time;
    }

}
