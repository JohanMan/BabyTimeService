package com.johan.service.member.dao;

import com.johan.service.member.entity.RolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}