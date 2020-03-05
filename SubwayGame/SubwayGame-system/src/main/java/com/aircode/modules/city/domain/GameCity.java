package com.aircode.modules.city.domain;

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
@Table(name="game_city")
public class GameCity implements Serializable {

    // 城市编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    // 城市名称
    @Column(name = "city_name",nullable = false)
    private String cityName;

    // 所属省份
    @Column(name = "sheng_name",nullable = false)
    private String shengName;

    // 所属市级
    @Column(name = "shi_name",nullable = false)
    private String shiName;

    // 是否启用 0/否 1/是
    @Column(name = "start")
    private Integer start;

    public void copy(GameCity source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}