package com.aircode.modules.site.service;

import com.aircode.modules.site.domain.GameSite;
import com.aircode.modules.site.service.dto.GameSiteDTO;
import com.aircode.modules.site.service.dto.GameSiteQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameSiteService {

    /**
     * 查询所有站点
     * @return
     */
    public List<GameSite> findAll();

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameSiteQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameSiteDTO>
    */
    List<GameSiteDTO> queryAll(GameSiteQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param siteId ID
     * @return GameSiteDTO
     */
    GameSiteDTO findById(Integer siteId);

    GameSiteDTO create(GameSite resources);

    void update(GameSite resources);

    void delete(Integer siteId);

    void download(List<GameSiteDTO> all, HttpServletResponse response) throws IOException;
}