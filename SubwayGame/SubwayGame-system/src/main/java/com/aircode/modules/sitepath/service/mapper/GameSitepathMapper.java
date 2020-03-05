package com.aircode.modules.sitepath.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.sitepath.domain.GameSitepath;
import com.aircode.modules.sitepath.service.dto.GameSitepathDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameSitepathMapper extends BaseMapper<GameSitepathDTO, GameSitepath> {

}