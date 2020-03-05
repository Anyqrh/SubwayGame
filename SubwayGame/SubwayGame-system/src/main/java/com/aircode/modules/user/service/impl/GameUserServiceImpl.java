package com.aircode.modules.user.service.impl;

import com.aircode.modules.user.domain.GameUser;
import com.aircode.modules.user.repository.GameUserRepository;
import com.aircode.modules.user.service.GameUserService;
import com.aircode.modules.user.service.dto.GameUserDTO;
import com.aircode.modules.user.service.dto.GameUserQueryCriteria;
import com.aircode.modules.user.service.mapper.GameUserMapper;
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
* @date 2020-02-27
*/
@Service
@CacheConfig(cacheNames = "gameUser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameUserServiceImpl implements GameUserService {

    private final GameUserRepository gameUserRepository;

    private final GameUserMapper gameUserMapper;

    public GameUserServiceImpl(GameUserRepository gameUserRepository, GameUserMapper gameUserMapper) {
        this.gameUserRepository = gameUserRepository;
        this.gameUserMapper = gameUserMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameUserQueryCriteria criteria, Pageable pageable){
        Page<GameUser> page = gameUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameUserMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameUserDTO> queryAll(GameUserQueryCriteria criteria){
        return gameUserMapper.toDto(gameUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameUserDTO findById(Integer gameId) {
        GameUser gameUser = gameUserRepository.findById(gameId).orElseGet(GameUser::new);
        ValidationUtil.isNull(gameUser.getGameId(),"GameUser","gameId",gameId);
        return gameUserMapper.toDto(gameUser);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameUserDTO create(GameUser resources) {
        return gameUserMapper.toDto(gameUserRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameUser resources) {
        GameUser gameUser = gameUserRepository.findById(resources.getGameId()).orElseGet(GameUser::new);
        ValidationUtil.isNull( gameUser.getGameId(),"GameUser","id",resources.getGameId());
        gameUser.copy(resources);
        gameUserRepository.save(gameUser);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer gameId) {
        gameUserRepository.deleteById(gameId);
    }


    @Override
    public void download(List<GameUserDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameUserDTO gameUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户账号", gameUser.getGameAccount());
            map.put("昵称", gameUser.getGameName());
            map.put("头像路径", gameUser.getGameAvatar());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}