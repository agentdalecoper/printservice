package ru.nvg.printservice.qdsl;

import lombok.Data;
import ru.nvg.printservice.domain.JobType;

import java.time.LocalDateTime;

@Data
public class StatisticFilter {
    private String user;
    private JobType type;
    private String device;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
}
