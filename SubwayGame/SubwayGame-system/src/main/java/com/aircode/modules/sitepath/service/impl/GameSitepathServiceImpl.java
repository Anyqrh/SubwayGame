package com.aircode.modules.sitepath.service.impl;

import com.aircode.modules.sitepath.domain.GameSitepath;
import com.aircode.modules.sitepath.repository.GameSitepathRepository;
import com.aircode.modules.sitepath.service.GameSitepathService;
import com.aircode.modules.sitepath.service.dto.GameSitepathDTO;
import com.aircode.modules.sitepath.service.dto.GameSitepathQueryCriteria;
import com.aircode.modules.sitepath.service.mapper.GameSitepathMapper;
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
@CacheConfig(cacheNames = "gameSitepath")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameSitepathServiceImpl implements GameSitepathService {

    private final GameSitepathRepository gameSitepathRepository;

    private final GameSitepathMapper gameSitepathMapper;

    public GameSitepathServiceImpl(GameSitepathRepository gameSitepathRepository, GameSitepathMapper gameSitepathMapper) {
        this.gameSitepathRepository = gameSitepathRepository;
        this.gameSitepathMapper = gameSitepathMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameSitepathQueryCriteria criteria, Pageable pageable){
        Page<GameSitepath> page = gameSitepathRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameSitepathMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameSitepathDTO> queryAll(GameSitepathQueryCriteria criteria){
        return gameSitepathMapper.toDto(gameSitepathRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameSitepathDTO findById(Integer sitepathId) {
        GameSitepath gameSitepath = gameSitepathRepository.findById(sitepathId).orElseGet(GameSitepath::new);
        ValidationUtil.isNull(gameSitepath.getSitepathId(),"GameSitepath","sitepathId",sitepathId);
        return gameSitepathMapper.toDto(gameSitepath);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameSitepathDTO create(GameSitepath resources) {
        return gameSitepathMapper.toDto(gameSitepathRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameSitepath resources) {
        GameSitepath gameSitepath = gameSitepathRepository.findById(resources.getSitepathId()).orElseGet(GameSitepath::new);
        ValidationUtil.isNull( gameSitepath.getSitepathId(),"GameSitepath","id",resources.getSitepathId());
        gameSitepath.copy(resources);
        gameSitepathRepository.save(gameSitepath);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer sitepathId) {
        gameSitepathRepository.deleteById(sitepathId);
    }


    @Override
    public void download(List<GameSitepathDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameSitepathDTO gameSitepath : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("所属用户编号", gameSitepath.getUserId());
            map.put("城市名称", gameSitepath.getCityName());
            map.put("站点出发地", gameSitepath.getSiteFrom());
            map.put("目的地", gameSitepath.getSiteOn());
            map.put("是否完成 0/否 1/是", gameSitepath.getFinish());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}