package ru.hse.glassofwater.dto;

import lombok.Data;

@Data
public class FriendInviteDto {
    private String initiatorUsername;
    private String receiverUsername;

}
