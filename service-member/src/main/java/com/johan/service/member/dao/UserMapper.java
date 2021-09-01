package com.johan.service.member.dao;

import com.johan.service.member.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByMobile(String mobile);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}