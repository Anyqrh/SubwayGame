package com.aircode.modules.city.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.city.domain.GameCity;
import com.aircode.modules.city.service.dto.GameCityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameCityMapper extends BaseMapper<GameCityDTO, GameCity> {

}