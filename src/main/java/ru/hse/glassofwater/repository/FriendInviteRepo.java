package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.glassofwater.model.FriendInvite;

@Repository
public interface FriendInviteRepo extends JpaRepository<FriendInvite, Long> {

}
