package com.johan.service.member.dao;

import com.johan.service.member.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}