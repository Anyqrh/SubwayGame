package com.aircode.modules.user.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-27
*/
@Data
public class GameUserDTO implements Serializable {

    // 用户编号
    private Integer gameId;

    // 用户账号
    private String gameAccount;

    // 昵称
    private String gameName;

    // 头像路径
    private String gameAvatar;
}