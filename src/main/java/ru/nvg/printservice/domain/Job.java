package ru.nvg.printservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


//ToDo rename it to not a job
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(schema = "public", name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public JobType type;

    @OneToOne
    public User user;

    public int amount;

    public LocalDateTime time;
}
