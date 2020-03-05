package com.aircode.modules.sheng.service.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameShengMapper01 {
    @Select("select count(*) from game_sheng where sheng_name = #{sheng_name}")
    int findExitsByShengName(String sheng_name);
}
