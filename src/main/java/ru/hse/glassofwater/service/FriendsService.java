package ru.hse.glassofwater.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.dto.FriendInviteDto;
import ru.hse.glassofwater.model.FriendInvite;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.FriendInviteRepo;
import ru.hse.glassofwater.repository.UserRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final UserService userService;
    private final UserRepo userRepo;
    private final FriendInviteRepo friendInviteRepo;

    public void addInvite(FriendInviteDto invite) {
        User receiver;
        User initiator;
        try {
            receiver = userService.getUserByUsername(invite.getReceiverUsername());
            initiator = userService.getUserByUsername(invite.getInitiatorUsername());
        } catch (NoSuchElementException e) {
            return;
        }

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
        return allUsers.stream().filter(user -> !currentUser.getFriends().contains(user) && !user.equals(currentUser)).collect(Collectors.toList());
    }
}
