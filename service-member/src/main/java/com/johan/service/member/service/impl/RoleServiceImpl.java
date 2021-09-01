package com.johan.service.member.service.impl;

import com.johan.service.member.dao.RoleMapper;
import com.johan.service.member.entity.Role;
import com.johan.service.member.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findByUserId(String userId) {
        return roleMapper.selectByUserId(userId);
    }

}
