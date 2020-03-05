package com.aircode.modules.sheng.service;

import com.aircode.modules.sheng.domain.GameSheng;
import com.aircode.modules.sheng.service.dto.GameShengDTO;
import com.aircode.modules.sheng.service.dto.GameShengQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameShengService {

    /**
     * 根据省名称查询数据
     * @param shengName
     * @return
     */
    public int findExitsByShengName(String shengName);

    /**
     * 查询所有省份
     * @return
     */
    public List<GameSheng> findAll();


    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameShengQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameShengDTO>
    */
    List<GameShengDTO> queryAll(GameShengQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param shengId ID
     * @return GameShengDTO
     */
    GameShengDTO findById(Integer shengId);

    GameShengDTO create(GameSheng resources);

    void update(GameSheng resources);

    void delete(Integer shengId);

    void download(List<GameShengDTO> all, HttpServletResponse response) throws IOException;
}