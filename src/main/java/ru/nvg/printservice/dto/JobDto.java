package ru.nvg.printservice.dto;

import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;

import javax.persistence.Entity;

public class JobDto {
    public Long id;
    public JobType type;
    public User user;
    public int amount;
}
