package com.aircode.modules.sheng.service.impl;

import com.aircode.modules.sheng.domain.GameSheng;
import com.aircode.modules.sheng.repository.GameShengRepository;
import com.aircode.modules.sheng.service.GameShengService;
import com.aircode.modules.sheng.service.dto.GameShengDTO;
import com.aircode.modules.sheng.service.dto.GameShengQueryCriteria;
import com.aircode.modules.sheng.service.mapper.GameShengMapper;
import com.aircode.modules.sheng.service.mapper.GameShengMapper01;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Zeta
* @date 2020-02-28
*/
@Service
@CacheConfig(cacheNames = "gameSheng")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameShengServiceImpl implements GameShengService {

    @Resource
    private GameShengMapper01 gameShengMapper01;

    private final GameShengRepository gameShengRepository;

    private final GameShengMapper gameShengMapper;

    public GameShengServiceImpl(GameShengRepository gameShengRepository, GameShengMapper gameShengMapper) {
        this.gameShengRepository = gameShengRepository;
        this.gameShengMapper = gameShengMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameShengQueryCriteria criteria, Pageable pageable){
        Page<GameSheng> page = gameShengRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameShengMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameShengDTO> queryAll(GameShengQueryCriteria criteria){
        return gameShengMapper.toDto(gameShengRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameShengDTO findById(Integer shengId) {
        GameSheng gameSheng = gameShengRepository.findById(shengId).orElseGet(GameSheng::new);
        ValidationUtil.isNull(gameSheng.getShengId(),"GameSheng","shengId",shengId);
        return gameShengMapper.toDto(gameSheng);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameShengDTO create(GameSheng resources) {
        return gameShengMapper.toDto(gameShengRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameSheng resources) {
        GameSheng gameSheng = gameShengRepository.findById(resources.getShengId()).orElseGet(GameSheng::new);
        ValidationUtil.isNull( gameSheng.getShengId(),"GameSheng","id",resources.getShengId());
        gameSheng.copy(resources);
        gameShengRepository.save(gameSheng);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer shengId) {
        gameShengRepository.deleteById(shengId);
    }


    @Override
    public void download(List<GameShengDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameShengDTO gameSheng : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("省", gameSheng.getShengName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    public List<GameSheng> findAll(){
        return gameShengRepository.findAll();
    }

    public int findExitsByShengName(String shengName){
        return gameShengMapper01.findExitsByShengName(shengName);
    }
}