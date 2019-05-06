package ru.nvg.printservice.dto;

import java.util.HashMap;
import java.util.Map;

public class JobSummaryDto {
    public Map<String, Integer> summaryForTransaction;

    public JobSummaryDto()
    {
        summaryForTransaction = new HashMap<>();
    }
}
