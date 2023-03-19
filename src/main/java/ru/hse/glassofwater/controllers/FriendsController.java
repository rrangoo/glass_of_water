package ru.hse.glassofwater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.dto.FriendInviteDto;
import ru.hse.glassofwater.model.FriendInvite;
import ru.hse.glassofwater.service.FriendsService;

import java.util.List;

@RestController
@RequestMapping("friends")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping()
    public ResponseEntity<Void> sendInvite(@RequestBody FriendInviteDto invite) {
        friendsService.addInvite(invite);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("invite/{id}")
    public ResponseEntity<Void> acceptInvite(@PathVariable Long id) {
        friendsService.acceptInvite(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("invite/")
    public ResponseEntity<List<FriendInvite>> getInvitesByUserId(@RequestParam(name = "user_id") Long userId) {
        return ResponseEntity.ok().body(friendsService.getInvitesByUserId(userId));
    }

    @GetMapping("initiation/")
    public ResponseEntity<List<FriendInvite>> getInitiationsByUserId(@RequestParam(name = "user_id") Long userId) {
        return ResponseEntity.ok().body(friendsService.getInitiationsByUserId(userId));
    }

}
