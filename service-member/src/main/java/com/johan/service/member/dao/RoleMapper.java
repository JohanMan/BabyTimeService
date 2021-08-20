package com.johan.service.member.dao;

import com.johan.service.member.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}