package com.aircode.modules.sitepath.service;

import com.aircode.modules.sitepath.domain.GameSitepath;
import com.aircode.modules.sitepath.service.dto.GameSitepathDTO;
import com.aircode.modules.sitepath.service.dto.GameSitepathQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameSitepathService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameSitepathQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameSitepathDTO>
    */
    List<GameSitepathDTO> queryAll(GameSitepathQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param sitepathId ID
     * @return GameSitepathDTO
     */
    GameSitepathDTO findById(Integer sitepathId);

    GameSitepathDTO create(GameSitepath resources);

    void update(GameSitepath resources);

    void delete(Integer sitepathId);

    void download(List<GameSitepathDTO> all, HttpServletResponse response) throws IOException;
}