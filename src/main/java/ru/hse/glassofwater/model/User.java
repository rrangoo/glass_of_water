package ru.hse.glassofwater.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;
    private String email;
    private Integer rate;

    @OneToMany
    private List<Trip> trips;

    // Отправленные заявки пользователя
    @JsonIgnore
    @OneToMany(mappedBy="initiator")
    private List<FriendInvite> usersSubscriptions;

    // Приглашения в друзья
    @JsonIgnore
    @OneToMany(mappedBy="receiver")
    private List<FriendInvite> usersInvitations;
}
