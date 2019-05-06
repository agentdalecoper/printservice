package ru.nvg.printservice.qdsl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nvg.printservice.domain.JobType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticFilter {
    private String user;
    private String device;
    private JobType type;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
}
