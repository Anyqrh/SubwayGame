package com.aircode.modules.friendlist.service;

import com.aircode.modules.friendlist.domain.GameFriendList;
import com.aircode.modules.friendlist.service.dto.GameFriendListDTO;
import com.aircode.modules.friendlist.service.dto.GameFriendListQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameFriendListService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameFriendListQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameFriendListDTO>
    */
    List<GameFriendListDTO> queryAll(GameFriendListQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param friendlistId ID
     * @return GameFriendListDTO
     */
    GameFriendListDTO findById(Integer friendlistId);

    GameFriendListDTO create(GameFriendList resources);

    void update(GameFriendList resources);

    void delete(Integer friendlistId);

    void download(List<GameFriendListDTO> all, HttpServletResponse response) throws IOException;
}