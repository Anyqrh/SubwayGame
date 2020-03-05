package com.aircode.modules.sitepath.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameSitepathDTO implements Serializable {

    // 站点路线编号
    private Integer sitepathId;

    // 所属用户编号
    private Integer userId;

    // 城市名称
    private String cityName;

    // 站点出发地
    private Integer siteFrom;

    // 目的地
    private Integer siteOn;

    // 是否完成 0/否 1/是
    private Integer finish;
}