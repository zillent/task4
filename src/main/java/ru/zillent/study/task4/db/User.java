package ru.zillent.study.task4.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private int id;
    private String username;
    private String fio;

//    @OneToMany(mappedBy = "logins", cascade = CascadeType.ALL, orphanRemoval = true)
//    Set<Login> logins;

}
