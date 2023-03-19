package ru.hse.glassofwater.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "friend_invite")
public class FriendInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Тот, кто оправил заявку в друзья
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    // Тот, кто получил заявку в друзья
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Boolean accepted;

    private LocalDate acceptanceDate;

    private LocalDate inviteDate;

    public FriendInvite() {

    }
}
