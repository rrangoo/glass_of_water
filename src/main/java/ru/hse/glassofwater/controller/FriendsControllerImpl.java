package ru.hse.glassofwater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.glassofwater.dto.FriendInviteDto;
import ru.hse.glassofwater.model.FriendInvite;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.service.FriendsService;

import java.util.List;

@RestController
@RequestMapping("friends")
@RequiredArgsConstructor
public class FriendsControllerImpl implements FriendsController {

    private final FriendsService friendsService;

    @Override
    public void sendInvite(FriendInviteDto invite) {
        friendsService.addInvite(invite);
    }

    @Override
    public void acceptInvite(Long inviteId) {
        friendsService.acceptInvite(inviteId);
    }

    @Override
    public List<FriendInvite> getInvitesByUserId(Long userId) {
        return friendsService.getInvitesByUserId(userId);
    }

    @Override
    public List<FriendInvite> getInitiationsByUserId(Long userId) {
        return friendsService.getInitiationsByUserId(userId);
    }

    @Override
    public List<User> getFriends(Long id) {
        return friendsService.getFriends(id);
    }

    @Override
    public List<User> getNotFriends(Long userId) {
        return friendsService.getNotFriends(userId);
    }
}
