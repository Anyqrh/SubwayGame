package com.aircode.modules.subwaypath.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.subwaypath.domain.GameSubwaypath;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameSubwaypathMapper extends BaseMapper<GameSubwaypathDTO, GameSubwaypath> {

}