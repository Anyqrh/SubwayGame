package com.aircode.modules.user.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.user.domain.GameUser;
import com.aircode.modules.user.service.dto.GameUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-27
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameUserMapper extends BaseMapper<GameUserDTO, GameUser> {

}