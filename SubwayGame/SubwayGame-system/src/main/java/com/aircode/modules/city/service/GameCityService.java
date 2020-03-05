package com.aircode.modules.city.service;

import com.aircode.modules.city.domain.GameCity;
import com.aircode.modules.city.service.dto.GameCityDTO;
import com.aircode.modules.city.service.dto.GameCityQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameCityService {

    /**
     * 查询所有城市
     * @return
     */
    public List<GameCity> findAll();

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameCityQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameCityDTO>
    */
    List<GameCityDTO> queryAll(GameCityQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param cityId ID
     * @return GameCityDTO
     */
    GameCityDTO findById(Integer cityId);

    GameCityDTO create(GameCity resources);

    void update(GameCity resources);

    void delete(Integer cityId);

    void download(List<GameCityDTO> all, HttpServletResponse response) throws IOException;
}