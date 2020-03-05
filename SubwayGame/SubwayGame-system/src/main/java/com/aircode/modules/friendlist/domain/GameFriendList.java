package com.aircode.modules.friendlist.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author Zeta
* @date 2020-02-28
*/
@Entity
@Data
@Table(name="game_friend_list")
public class GameFriendList implements Serializable {

    // 好友列表编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendlist_id")
    private Integer friendlistId;

    // 用户编号
    @Column(name = "game_id",nullable = false)
    private Integer gameId;

    // 好友编号
    @Column(name = "friend_id",nullable = false)
    private Integer friendId;

    public void copy(GameFriendList source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}