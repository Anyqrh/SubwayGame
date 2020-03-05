package com.aircode.modules.friendlist.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameFriendListDTO implements Serializable {

    // 好友列表编号
    private Integer friendlistId;

    // 用户编号
    private Integer gameId;

    // 好友编号
    private Integer friendId;
}