package com.johan.service.member.service.impl;

import com.johan.common.utils.SnowFlake;
import com.johan.service.member.dao.UserRoleMapper;
import com.johan.service.member.entity.UserRole;
import com.johan.service.member.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public int insert(String userId, int roleId) {
        String id = String.valueOf(snowFlake.nextId());
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUid(userId);
        userRole.setRid(roleId);
        return userRoleMapper.insertSelective(userRole);
    }

}
