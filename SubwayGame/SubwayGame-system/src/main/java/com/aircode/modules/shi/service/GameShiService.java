package com.aircode.modules.shi.service;

import com.aircode.modules.shi.domain.GameShi;
import com.aircode.modules.shi.service.dto.GameShiDTO;
import com.aircode.modules.shi.service.dto.GameShiQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameShiService {

    /**
     * 根据市级名称查询该市名称是否存在
     * @param shiName
     * @return
     */
    public int findExistByShiName(String shiName, String shengName);

    /**
     * 根据省份查找市级
     * @param shengName
     * @return
     */
    public List<String> findAllByShengName(String shengName);

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameShiQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameShiDTO>
    */
    List<GameShiDTO> queryAll(GameShiQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param shiId ID
     * @return GameShiDTO
     */
    GameShiDTO findById(Integer shiId);

    GameShiDTO create(GameShi resources);

    void update(GameShi resources);

    void delete(Integer shiId);

    void download(List<GameShiDTO> all, HttpServletResponse response) throws IOException;
}