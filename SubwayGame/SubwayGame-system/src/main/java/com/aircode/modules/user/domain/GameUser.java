package com.aircode.modules.user.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author Zeta
* @date 2020-02-27
*/
@Entity
@Data
@Table(name="game_user")
public class GameUser implements Serializable {

    // 用户编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameId;

    // 用户账号
    @Column(name = "game_account",nullable = false)
    private String gameAccount;

    // 昵称
    @Column(name = "game_name",nullable = false)
    private String gameName;

    // 头像路径
    @Column(name = "game_avatar")
    private String gameAvatar;

    public void copy(GameUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}