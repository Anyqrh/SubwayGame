package com.aircode.modules.subwaypath.domain;

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
@Table(name="game_subwaypath")
public class GameSubwaypath implements Serializable {

    // 地铁路线编号
    @Id
    @Column(name = "subwaypath_id")
    private Integer subwaypathId;

    // 地铁路线名称
    @Column(name = "subwaypath_name",nullable = false)
    private String subwaypathName;

    // 所属用户
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    // 城市名称
    @Column(name = "city_name",nullable = false)
    private String cityName;

    // 地铁路线
    @Column(name = "path")
    private String path;

    // 是否完成 0/否 1/是
    @Column(name = "finish")
    private Integer finish;

    public void copy(GameSubwaypath source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}