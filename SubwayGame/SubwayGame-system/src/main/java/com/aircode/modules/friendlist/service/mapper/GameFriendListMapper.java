package com.aircode.modules.friendlist.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.friendlist.domain.GameFriendList;
import com.aircode.modules.friendlist.service.dto.GameFriendListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameFriendListMapper extends BaseMapper<GameFriendListDTO, GameFriendList> {

}