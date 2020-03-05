package com.aircode.modules.subwaypath.service;

import com.aircode.modules.subwaypath.domain.GameSubwaypath;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathDTO;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameSubwaypathService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameSubwaypathQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameSubwaypathDTO>
    */
    List<GameSubwaypathDTO> queryAll(GameSubwaypathQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param subwaypathId ID
     * @return GameSubwaypathDTO
     */
    GameSubwaypathDTO findById(Integer subwaypathId);

    GameSubwaypathDTO create(GameSubwaypath resources);

    void update(GameSubwaypath resources);

    void delete(Integer subwaypathId);

    void download(List<GameSubwaypathDTO> all, HttpServletResponse response) throws IOException;
}