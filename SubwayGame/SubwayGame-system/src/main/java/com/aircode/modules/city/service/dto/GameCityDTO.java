package com.aircode.modules.city.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameCityDTO implements Serializable {

    // 城市编号
    private Integer cityId;

    // 城市名称
    private String cityName;

    // 所属省份
    private String shengName;

    // 所属市级
    private String shiName;

    // 是否启用 0/否 1/是
    private Integer start;
}