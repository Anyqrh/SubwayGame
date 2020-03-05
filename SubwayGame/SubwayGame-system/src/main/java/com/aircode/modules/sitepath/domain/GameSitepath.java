package com.aircode.modules.sitepath.domain;

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
@Table(name="game_sitepath")
public class GameSitepath implements Serializable {

    // 站点路线编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sitepath_id")
    private Integer sitepathId;

    // 所属用户编号
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    // 城市名称
    @Column(name = "city_name",nullable = false)
    private String cityName;

    // 站点出发地
    @Column(name = "site_from",nullable = false)
    private Integer siteFrom;

    // 目的地
    @Column(name = "site_on",nullable = false)
    private Integer siteOn;

    // 是否完成 0/否 1/是
    @Column(name = "finish")
    private Integer finish;

    public void copy(GameSitepath source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}