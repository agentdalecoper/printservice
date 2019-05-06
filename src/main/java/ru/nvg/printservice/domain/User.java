package ru.nvg.printservice.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "user")
@ToString
@EqualsAndHashCode(of="name")
public class User {
    //ToDo Here too name of the user must be unique?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    public String name;

}