package com.aircode.modules.subwaypath.service.impl;

import com.aircode.modules.subwaypath.domain.GameSubwaypath;
import com.aircode.modules.subwaypath.repository.GameSubwaypathRepository;
import com.aircode.modules.subwaypath.service.GameSubwaypathService;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathDTO;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathQueryCriteria;
import com.aircode.modules.subwaypath.service.mapper.GameSubwaypathMapper;
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
@CacheConfig(cacheNames = "gameSubwaypath")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameSubwaypathServiceImpl implements GameSubwaypathService {

    private final GameSubwaypathRepository gameSubwaypathRepository;

    private final GameSubwaypathMapper gameSubwaypathMapper;

    public GameSubwaypathServiceImpl(GameSubwaypathRepository gameSubwaypathRepository, GameSubwaypathMapper gameSubwaypathMapper) {
        this.gameSubwaypathRepository = gameSubwaypathRepository;
        this.gameSubwaypathMapper = gameSubwaypathMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameSubwaypathQueryCriteria criteria, Pageable pageable){
        Page<GameSubwaypath> page = gameSubwaypathRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameSubwaypathMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameSubwaypathDTO> queryAll(GameSubwaypathQueryCriteria criteria){
        return gameSubwaypathMapper.toDto(gameSubwaypathRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameSubwaypathDTO findById(Integer subwaypathId) {
        GameSubwaypath gameSubwaypath = gameSubwaypathRepository.findById(subwaypathId).orElseGet(GameSubwaypath::new);
        ValidationUtil.isNull(gameSubwaypath.getSubwaypathId(),"GameSubwaypath","subwaypathId",subwaypathId);
        return gameSubwaypathMapper.toDto(gameSubwaypath);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameSubwaypathDTO create(GameSubwaypath resources) {
        return gameSubwaypathMapper.toDto(gameSubwaypathRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameSubwaypath resources) {
        GameSubwaypath gameSubwaypath = gameSubwaypathRepository.findById(resources.getSubwaypathId()).orElseGet(GameSubwaypath::new);
        ValidationUtil.isNull( gameSubwaypath.getSubwaypathId(),"GameSubwaypath","id",resources.getSubwaypathId());
        gameSubwaypath.copy(resources);
        gameSubwaypathRepository.save(gameSubwaypath);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer subwaypathId) {
        gameSubwaypathRepository.deleteById(subwaypathId);
    }


    @Override
    public void download(List<GameSubwaypathDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameSubwaypathDTO gameSubwaypath : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("地铁路线名称", gameSubwaypath.getSubwaypathName());
            map.put("所属用户", gameSubwaypath.getUserId());
            map.put("城市名称", gameSubwaypath.getCityName());
            map.put("地铁路线", gameSubwaypath.getPath());
            map.put("是否完成 0/否 1/是", gameSubwaypath.getFinish());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}