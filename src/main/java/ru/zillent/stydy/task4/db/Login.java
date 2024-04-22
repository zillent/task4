package ru.zillent.stydy.task4.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "logins")
public class Login {
    @Id
    @SequenceGenerator(name = "logins_seq", sequenceName = "logins_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logins_seq")
    private int id;
    @Column(name="access_date")
    private Date accessDate;
    //@Column(name="user_id")
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="application")
    private String application;
}
