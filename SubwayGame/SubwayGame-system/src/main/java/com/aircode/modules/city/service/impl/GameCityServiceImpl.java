package com.aircode.modules.city.service.impl;

import com.aircode.modules.city.domain.GameCity;
import com.aircode.modules.city.repository.GameCityRepository;
import com.aircode.modules.city.service.GameCityService;
import com.aircode.modules.city.service.dto.GameCityDTO;
import com.aircode.modules.city.service.dto.GameCityQueryCriteria;
import com.aircode.modules.city.service.mapper.GameCityMapper;
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
@CacheConfig(cacheNames = "gameCity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameCityServiceImpl implements GameCityService {

    private final GameCityRepository gameCityRepository;

    private final GameCityMapper gameCityMapper;

    public GameCityServiceImpl(GameCityRepository gameCityRepository, GameCityMapper gameCityMapper) {
        this.gameCityRepository = gameCityRepository;
        this.gameCityMapper = gameCityMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameCityQueryCriteria criteria, Pageable pageable){
        Page<GameCity> page = gameCityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameCityMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameCityDTO> queryAll(GameCityQueryCriteria criteria){
        return gameCityMapper.toDto(gameCityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameCityDTO findById(Integer cityId) {
        GameCity gameCity = gameCityRepository.findById(cityId).orElseGet(GameCity::new);
        ValidationUtil.isNull(gameCity.getCityId(),"GameCity","cityId",cityId);
        return gameCityMapper.toDto(gameCity);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameCityDTO create(GameCity resources) {
        return gameCityMapper.toDto(gameCityRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameCity resources) {
        GameCity gameCity = gameCityRepository.findById(resources.getCityId()).orElseGet(GameCity::new);
        ValidationUtil.isNull( gameCity.getCityId(),"GameCity","id",resources.getCityId());
        gameCity.copy(resources);
        gameCityRepository.save(gameCity);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer cityId) {
        gameCityRepository.deleteById(cityId);
    }


    @Override
    public void download(List<GameCityDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameCityDTO gameCity : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("城市名称", gameCity.getCityName());
            map.put("所属省份", gameCity.getShengName());
            map.put("所属市级", gameCity.getShiName());
            map.put("是否启用 0/否 1/是", gameCity.getStart());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    public List<GameCity> findAll(){
        return gameCityRepository.findAll();
    }
}