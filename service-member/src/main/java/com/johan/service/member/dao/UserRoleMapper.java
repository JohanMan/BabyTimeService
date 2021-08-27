package com.johan.service.member.dao;

import com.johan.service.member.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}