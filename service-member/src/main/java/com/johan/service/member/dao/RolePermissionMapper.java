package com.johan.service.member.dao;

import com.johan.service.member.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}