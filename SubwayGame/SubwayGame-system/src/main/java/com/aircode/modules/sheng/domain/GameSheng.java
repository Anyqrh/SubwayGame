package com.aircode.modules.sheng.domain;

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
@Table(name="game_sheng")
public class GameSheng implements Serializable {

    // 编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sheng_id")
    private Integer shengId;

    // 省
    @Column(name = "sheng_name",nullable = false)
    private String shengName;

    public void copy(GameSheng source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}