package ru.nvg.printservice.dto;

import ru.nvg.printservice.domain.User;

import java.util.HashMap;
import java.util.Map;

public class JobSummaryDto {
    public Map<User, Integer> summaryForTransaction;

    public JobSummaryDto()
    {
        summaryForTransaction = new HashMap<>();
    }
}
