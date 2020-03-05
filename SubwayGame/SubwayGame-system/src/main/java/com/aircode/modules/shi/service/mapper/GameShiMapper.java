package com.aircode.modules.shi.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.shi.domain.GameShi;
import com.aircode.modules.shi.service.dto.GameShiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameShiMapper extends BaseMapper<GameShiDTO, GameShi> {

}