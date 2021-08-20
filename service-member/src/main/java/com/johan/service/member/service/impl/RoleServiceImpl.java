package com.johan.service.member.service.impl;

import com.johan.common.util.SnowFlake;
import com.johan.service.member.dao.RoleMapper;
import com.johan.service.member.entity.Role;
import com.johan.service.member.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void insert(Role role) {
        String id = String.valueOf(snowFlake.nextId());
        role.setRid(id);
        roleMapper.insert(role);
    }

}
