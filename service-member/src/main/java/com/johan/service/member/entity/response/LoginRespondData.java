package com.johan.service.member.entity.response;

import com.johan.service.member.entity.Role;
import com.johan.service.member.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRespondData {

    private String token;
    private User user;
    private Role role;

}
