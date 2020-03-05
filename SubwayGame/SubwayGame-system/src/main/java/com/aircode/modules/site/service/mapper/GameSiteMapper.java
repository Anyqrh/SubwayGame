package com.aircode.modules.site.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.site.domain.GameSite;
import com.aircode.modules.site.service.dto.GameSiteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zeta
* @date 2020-02-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameSiteMapper extends BaseMapper<GameSiteDTO, GameSite> {

}