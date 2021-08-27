package com.johan.service.member.service.impl;

import com.johan.common.entity.GlobalException;
import com.johan.common.util.SnowFlake;
import com.johan.service.member.dao.UserMapper;
import com.johan.service.member.entity.User;
import com.johan.service.member.service.RoleService;
import com.johan.service.member.service.UserRoleService;
import com.johan.service.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Transactional
    @Override
    public void insertBgAdmin(User user) {
        insert(user, RoleService.ROLE_BG_ADMIN);
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        insert(user, RoleService.ROLE_USER);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @param roleId 角色ID
     */
    @Transactional
    public void insert(User user, int roleId) {
        // 创建用户ID
        String userId = String.valueOf(snowFlake.nextId());
        user.setUid(userId);
        // 添加用户
        int row = userMapper.insertSelective(user);
        if (row <= 0) {
            throw new GlobalException("添加用户失败");
        }
        // 添加用户角色
        row = userRoleService.insert(userId, roleId);
        if (row <= 0) {
            throw new GlobalException("添加用户失败");
        }
    }

}
