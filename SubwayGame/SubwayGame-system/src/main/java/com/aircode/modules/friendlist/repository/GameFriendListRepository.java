package com.aircode.modules.friendlist.repository;

import com.aircode.modules.friendlist.domain.GameFriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameFriendListRepository extends JpaRepository<GameFriendList, Integer>, JpaSpecificationExecutor<GameFriendList> {
}