package com.aircode.modules.sheng.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.sheng.domain.GameSheng;
import com.aircode.modules.sheng.service.dto.GameShengDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameShengMapper extends BaseMapper<GameShengDTO, GameSheng> {
}