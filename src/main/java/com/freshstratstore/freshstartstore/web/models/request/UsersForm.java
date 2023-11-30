package com.freshstratstore.freshstartstore.web.models.request;

import com.freshstratstore.freshstartstore.doa.entities.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsersForm {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String gendre;
    private Roles role;
}
