package com.proiect_final.proiect_final.dtos;

import com.proiect_final.proiect_final.entities.RoleType;

public class UserRequestDTO {

    private Long id;
    private String name;
    private RoleType roleType;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, RoleType roleType) {
        this.name = name;
        this.roleType = roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
