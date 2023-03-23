package ru.hse.glassofwater.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private Integer rate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
