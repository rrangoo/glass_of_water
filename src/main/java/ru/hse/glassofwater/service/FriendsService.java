package ru.hse.glassofwater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.dto.FriendInviteDto;
import ru.hse.glassofwater.model.FriendInvite;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.FriendInviteRepo;
import ru.hse.glassofwater.repository.UserRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class FriendsService {

    private final FriendInviteRepo friendInviteRepo;
    private final UserRepo userRepo;

    @Autowired
    public FriendsService(FriendInviteRepo friendInviteRepo, UserRepo userRepo) {
        this.friendInviteRepo = friendInviteRepo;
        this.userRepo = userRepo;
    }

    public void addInvite(FriendInviteDto invite) {
        User receiver = userRepo.findByUsername(invite.getReceiverUsername());
        User initiator = userRepo.findByUsername(invite.getInitiatorUsername());

        // TODO: обработать, если пользователь уже отправил заявку в друзья

        FriendInvite friendInvite = new FriendInvite();
        friendInvite.setInitiator(initiator);
        friendInvite.setReceiver(receiver);
        friendInvite.setAccepted(false);
        friendInvite.setInviteDate(LocalDate.now());

        receiver.getUsersInvitations().add(friendInvite);
        initiator.getUsersSubscriptions().add(friendInvite);

        friendInviteRepo.save(friendInvite);
        userRepo.save(receiver);
        userRepo.save(initiator);
    }

    public void acceptInvite(Long id) {
        FriendInvite friendInvite = friendInviteRepo.getReferenceById(id);

        friendInvite.setAccepted(true);
        friendInvite.setAcceptanceDate(LocalDate.now());

        friendInviteRepo.save(friendInvite);
    }

    public List<FriendInvite> getInvitesByUserId(Long userId) {
        User user = userRepo.findById(userId).get();
        return user.getUsersInvitations();
    }

    public List<FriendInvite> getInitiationsByUserId(Long userId) {
        User user = userRepo.findById(userId).get();
        return user.getUsersSubscriptions();
    }

}
