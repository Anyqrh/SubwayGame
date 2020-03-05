package com.aircode.modules.site.domain;

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
@Table(name="game_site")
public class GameSite implements Serializable {

    // 站点编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Integer siteId;

    // 站点名称
    @Column(name = "site_name",nullable = false)
    private String siteName;

    // 所属城市
    @Column(name = "city_name",nullable = false)
    private Integer cityName;

    // 是否启用 0/否 1/是
    @Column(name = "start")
    private Integer start;

    public void copy(GameSite source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}