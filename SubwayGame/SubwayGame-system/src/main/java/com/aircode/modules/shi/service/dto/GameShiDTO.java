package com.aircode.modules.shi.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameShiDTO implements Serializable {

    // 编号
    private Integer shiId;

    // 市
    private String shiName;

    // 所属省份
    private String shengName;
}