package com.johan.service.member.security;

import com.johan.service.member.entity.Role;
import com.johan.service.member.entity.User;
import com.johan.service.member.service.RoleService;
import com.johan.service.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class WebUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByMobile(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名/密码错误");
        }
        Role role = roleService.findByUserId(user.getUid());
        return new WebUserDetail(user, role);
    }

}
