package com.events.app.dtos;

import com.events.app.entities.RoleType;

public class UserRequestDTO {

    private String name;
    private RoleType roleType;

    private String userName;

    private String email;



    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, RoleType roleType) {
        this.name = name;
        this.roleType = roleType;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
