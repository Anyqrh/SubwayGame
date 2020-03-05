package com.aircode.modules.user.service;

import com.aircode.modules.user.domain.GameUser;
import com.aircode.modules.user.service.dto.GameUserDTO;
import com.aircode.modules.user.service.dto.GameUserQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-27
*/
public interface GameUserService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GameUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GameUserDTO>
    */
    List<GameUserDTO> queryAll(GameUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param gameId ID
     * @return GameUserDTO
     */
    GameUserDTO findById(Integer gameId);

    GameUserDTO create(GameUser resources);

    void update(GameUser resources);

    void delete(Integer gameId);

    void download(List<GameUserDTO> all, HttpServletResponse response) throws IOException;
}