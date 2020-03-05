package com.aircode.modules.site.service.impl;

import com.aircode.modules.site.domain.GameSite;
import com.aircode.modules.site.repository.GameSiteRepository;
import com.aircode.modules.site.service.GameSiteService;
import com.aircode.modules.site.service.dto.GameSiteDTO;
import com.aircode.modules.site.service.dto.GameSiteQueryCriteria;
import com.aircode.modules.site.service.mapper.GameSiteMapper;
import com.aircode.utils.FileUtil;
import com.aircode.utils.PageUtil;
import com.aircode.utils.QueryHelp;
import com.aircode.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Zeta
* @date 2020-02-28
*/
@Service
@CacheConfig(cacheNames = "gameSite")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameSiteServiceImpl implements GameSiteService {

    private final GameSiteRepository gameSiteRepository;

    private final GameSiteMapper gameSiteMapper;

    public GameSiteServiceImpl(GameSiteRepository gameSiteRepository, GameSiteMapper gameSiteMapper) {
        this.gameSiteRepository = gameSiteRepository;
        this.gameSiteMapper = gameSiteMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameSiteQueryCriteria criteria, Pageable pageable){
        Page<GameSite> page = gameSiteRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameSiteMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameSiteDTO> queryAll(GameSiteQueryCriteria criteria){
        return gameSiteMapper.toDto(gameSiteRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameSiteDTO findById(Integer siteId) {
        GameSite gameSite = gameSiteRepository.findById(siteId).orElseGet(GameSite::new);
        ValidationUtil.isNull(gameSite.getSiteId(),"GameSite","siteId",siteId);
        return gameSiteMapper.toDto(gameSite);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameSiteDTO create(GameSite resources) {
        return gameSiteMapper.toDto(gameSiteRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameSite resources) {
        GameSite gameSite = gameSiteRepository.findById(resources.getSiteId()).orElseGet(GameSite::new);
        ValidationUtil.isNull( gameSite.getSiteId(),"GameSite","id",resources.getSiteId());
        gameSite.copy(resources);
        gameSiteRepository.save(gameSite);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer siteId) {
        gameSiteRepository.deleteById(siteId);
    }


    @Override
    public void download(List<GameSiteDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameSiteDTO gameSite : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("站点名称", gameSite.getSiteName());
            map.put("所属城市", gameSite.getCityName());
            map.put("是否启用 0/否 1/是", gameSite.getStart());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    public List<GameSite> findAll(){
        return gameSiteRepository.findAll();
    }
}