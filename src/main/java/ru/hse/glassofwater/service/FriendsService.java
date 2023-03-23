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
import java.util.stream.Collectors;

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

        if (friendInvite.getAccepted()) {
            return;
        }

        friendInvite.setAccepted(true);
        friendInvite.setAcceptanceDate(LocalDate.now());

        User user1 = userRepo.findById(friendInvite.getReceiver().getId()).get();
        User user2 = userRepo.findById(friendInvite.getInitiator().getId()).get();

        user1.getFriends().add(user2);
        user2.getFriends().add(user1);

        userRepo.save(user1);
        userRepo.save(user2);

        friendInviteRepo.save(friendInvite);
    }

    public List<FriendInvite> getInvitesByUserId(Long userId) {
        User user = userRepo.findById(userId).get();
        // TODO: в будущем возможно нужно будет возвращать еще инвайты в статусе accepted == trye
        return user.getUsersInvitations().stream().filter(invite -> !invite.getAccepted()).collect(Collectors.toList());
    }

    public List<FriendInvite> getInitiationsByUserId(Long userId) {
        User user = userRepo.findById(userId).get();
        return user.getUsersSubscriptions();
    }

    public List<User> getFriends(Long userId) {
        return userRepo.findById(userId).get().getFriends();
    }

    public List<User> getNotFriends(Long userId) {
        User currentUser = userRepo.findById(userId).get();
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream().filter(user -> !currentUser.getFriends().contains(user)).collect(Collectors.toList());
    }
}
