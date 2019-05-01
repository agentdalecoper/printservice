package ru.nvg.printservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "user")
public class User {
    //ToDo Here too name of the user must be unique?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
