package com.example.practice_mobilelele.model.entity;

import com.example.practice_mobilelele.model.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return name;
    }

    public void setRole(RoleEnum name) {
        this.name = name;
    }
}
