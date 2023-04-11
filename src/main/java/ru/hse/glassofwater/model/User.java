package ru.hse.glassofwater.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "level", nullable = false)
    private String level;

    @OneToMany
    private List<Trip> trips;

    // Отправленные заявки пользователя
    @JsonIgnore
    @OneToMany(mappedBy = "initiator")
    private List<FriendInvite> usersSubscriptions;

    // Приглашения в друзья
    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private List<FriendInvite> usersInvitations;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;
}

