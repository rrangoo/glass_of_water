package ru.hse.glassofwater.controller;

import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.dto.FriendInviteDto;
import ru.hse.glassofwater.model.FriendInvite;
import ru.hse.glassofwater.model.User;

import java.util.List;

public interface FriendsController {
    @PostMapping
    void sendInvite(@RequestBody FriendInviteDto invite);

    @PatchMapping("invite/{inviteId}")
    void acceptInvite(@PathVariable Long inviteId);

    @GetMapping("invite/")
    List<FriendInvite> getInvitesByUserId(@RequestParam(name = "user_id") Long userId);

    @GetMapping("initiation/")
    List<FriendInvite> getInitiationsByUserId(@RequestParam(name = "user_id") Long userId);

    @GetMapping("{id}")
    List<User> getFriends(@PathVariable Long id);

    @GetMapping("{userId}/not_friends")
    List<User> getNotFriends(@PathVariable Long userId);
}
