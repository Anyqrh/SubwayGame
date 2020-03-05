package com.aircode.modules.sheng.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameShengDTO implements Serializable {

    // 编号
    private Integer shengId;

    // 省
    private String shengName;
}