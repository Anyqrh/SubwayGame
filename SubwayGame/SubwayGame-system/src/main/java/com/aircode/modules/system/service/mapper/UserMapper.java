package com.aircode.modules.system.service.mapper;

import com.aircode.base.BaseMapper;
import com.aircode.modules.system.domain.User;
import com.aircode.modules.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDTO, User> {

    @Mapping(source = "user.userAvatar.realName",target = "avatar")
    UserDTO toDto(User user);
}
