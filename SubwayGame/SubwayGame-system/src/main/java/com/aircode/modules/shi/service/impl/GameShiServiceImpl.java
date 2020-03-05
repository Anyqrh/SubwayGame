package com.aircode.modules.shi.service.impl;

import com.aircode.modules.shi.domain.GameShi;
import com.aircode.modules.shi.repository.GameShiRepository;
import com.aircode.modules.shi.service.GameShiService;
import com.aircode.modules.shi.service.dto.GameShiDTO;
import com.aircode.modules.shi.service.dto.GameShiQueryCriteria;
import com.aircode.modules.shi.service.mapper.GameShiMapper;
import com.aircode.modules.shi.service.mapper.GameShiMapper01;
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
@CacheConfig(cacheNames = "gameShi")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameShiServiceImpl implements GameShiService {

    @Resource
    private GameShiMapper01 gameShiMapper01;

    private final GameShiRepository gameShiRepository;

    private final GameShiMapper gameShiMapper;

    public GameShiServiceImpl(GameShiRepository gameShiRepository, GameShiMapper gameShiMapper) {
        this.gameShiRepository = gameShiRepository;
        this.gameShiMapper = gameShiMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameShiQueryCriteria criteria, Pageable pageable){
        Page<GameShi> page = gameShiRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameShiMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameShiDTO> queryAll(GameShiQueryCriteria criteria){
        return gameShiMapper.toDto(gameShiRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameShiDTO findById(Integer shiId) {
        GameShi gameShi = gameShiRepository.findById(shiId).orElseGet(GameShi::new);
        ValidationUtil.isNull(gameShi.getShiId(),"GameShi","shiId",shiId);
        return gameShiMapper.toDto(gameShi);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameShiDTO create(GameShi resources) {
        return gameShiMapper.toDto(gameShiRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameShi resources) {
        GameShi gameShi = gameShiRepository.findById(resources.getShiId()).orElseGet(GameShi::new);
        ValidationUtil.isNull( gameShi.getShiId(),"GameShi","id",resources.getShiId());
        gameShi.copy(resources);
        gameShiRepository.save(gameShi);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer shiId) {
        gameShiRepository.deleteById(shiId);
    }


    @Override
    public void download(List<GameShiDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameShiDTO gameShi : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("市", gameShi.getShiName());
            map.put("所属省份", gameShi.getShengName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    public List<String> findAllByShengName(String shengName){
        return gameShiMapper01.findAllByShengName(shengName);
    }
    public int findExistByShiName(String shiName, String shengName){
        return gameShiMapper01.findExistByShiName(shiName,shengName);
    }
}