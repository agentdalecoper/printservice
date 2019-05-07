package ru.nvg.printservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/*
   Basic representation of user job on device.
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "job")
public class Job {
    @Id
    private Long id;

    @NotNull
    public JobType type;

    @OneToOne
    @NotNull
    public User user;

    @OneToOne
    @NotNull
    public Device device;

    @NotNull
    public int amount;

    public LocalDateTime time;
}
