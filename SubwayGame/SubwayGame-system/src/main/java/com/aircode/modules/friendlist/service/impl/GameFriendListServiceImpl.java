package com.aircode.modules.friendlist.service.impl;

import com.aircode.modules.friendlist.domain.GameFriendList;
import com.aircode.modules.friendlist.repository.GameFriendListRepository;
import com.aircode.modules.friendlist.service.GameFriendListService;
import com.aircode.modules.friendlist.service.dto.GameFriendListDTO;
import com.aircode.modules.friendlist.service.dto.GameFriendListQueryCriteria;
import com.aircode.modules.friendlist.service.mapper.GameFriendListMapper;
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
@CacheConfig(cacheNames = "gameFriendList")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GameFriendListServiceImpl implements GameFriendListService {

    private final GameFriendListRepository gameFriendListRepository;

    private final GameFriendListMapper gameFriendListMapper;

    public GameFriendListServiceImpl(GameFriendListRepository gameFriendListRepository, GameFriendListMapper gameFriendListMapper) {
        this.gameFriendListRepository = gameFriendListRepository;
        this.gameFriendListMapper = gameFriendListMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(GameFriendListQueryCriteria criteria, Pageable pageable){
        Page<GameFriendList> page = gameFriendListRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameFriendListMapper::toDto));
    }

    @Override
    @Cacheable
    public List<GameFriendListDTO> queryAll(GameFriendListQueryCriteria criteria){
        return gameFriendListMapper.toDto(gameFriendListRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public GameFriendListDTO findById(Integer friendlistId) {
        GameFriendList gameFriendList = gameFriendListRepository.findById(friendlistId).orElseGet(GameFriendList::new);
        ValidationUtil.isNull(gameFriendList.getFriendlistId(),"GameFriendList","friendlistId",friendlistId);
        return gameFriendListMapper.toDto(gameFriendList);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GameFriendListDTO create(GameFriendList resources) {
        return gameFriendListMapper.toDto(gameFriendListRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GameFriendList resources) {
        GameFriendList gameFriendList = gameFriendListRepository.findById(resources.getFriendlistId()).orElseGet(GameFriendList::new);
        ValidationUtil.isNull( gameFriendList.getFriendlistId(),"GameFriendList","id",resources.getFriendlistId());
        gameFriendList.copy(resources);
        gameFriendListRepository.save(gameFriendList);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer friendlistId) {
        gameFriendListRepository.deleteById(friendlistId);
    }


    @Override
    public void download(List<GameFriendListDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameFriendListDTO gameFriendList : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户编号", gameFriendList.getGameId());
            map.put("好友编号", gameFriendList.getFriendId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}