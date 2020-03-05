package com.aircode.modules.shi.domain;

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
@Table(name="game_shi")
public class GameShi implements Serializable {

    // 编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shi_id")
    private Integer shiId;

    // 市
    @Column(name = "shi_name",nullable = false)
    private String shiName;

    // 所属省份
    @Column(name = "sheng_name",nullable = false)
    private String shengName;

    public void copy(GameShi source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}