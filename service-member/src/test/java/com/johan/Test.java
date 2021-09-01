package com.johan;

import com.johan.service.member.MemberApplication;
import com.johan.service.member.entity.Role;
import com.johan.service.member.entity.User;
import com.johan.service.member.service.RoleService;
import com.johan.service.member.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @org.junit.Test
    public void test() {
        User user = userService.findByMobile("18316623375");
        System.err.println(user);
        Role role = roleService.findByUserId(user.getUid());
        System.err.println(role);
    }

}
