package com.aircode.modules.site.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameSiteDTO implements Serializable {

    // 站点编号
    private Integer siteId;

    // 站点名称
    private String siteName;

    // 所属城市
    private Integer cityName;

    // 是否启用 0/否 1/是
    private Integer start;
}