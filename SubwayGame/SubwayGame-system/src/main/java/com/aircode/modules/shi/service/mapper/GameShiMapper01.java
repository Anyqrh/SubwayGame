package com.aircode.modules.shi.service.mapper;
import com.aircode.modules.shi.domain.GameShi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface GameShiMapper01 {

    @Select("select shi_name from game_shi where sheng_name = #{shengName}")
    public List<String> findAllByShengName(String shengName);

    @Select("select count(*) from game_shi where shi_name = #{shiName} and sheng_name = #{shengName}")
    public int findExistByShiName(String shiName, String shengName);

}
