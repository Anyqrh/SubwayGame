package com.aircode.modules.subwaypath.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zeta
* @date 2020-02-28
*/
@Data
public class GameSubwaypathDTO implements Serializable {

    // 地铁路线编号
    private Integer subwaypathId;

    // 地铁路线名称
    private String subwaypathName;

    // 所属用户
    private Integer userId;

    // 城市名称
    private String cityName;

    // 地铁路线
    private String path;

    // 是否完成 0/否 1/是
    private Integer finish;
}