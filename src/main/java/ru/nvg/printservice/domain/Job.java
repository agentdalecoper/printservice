package ru.nvg.printservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


//ToDo rename it to not a job
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public JobType type;

    @OneToOne
    @NotNull
    public User user;

    @OneToOne
    @NotNull
    public Device device;

    public int amount;

    public LocalDateTime time;
}
