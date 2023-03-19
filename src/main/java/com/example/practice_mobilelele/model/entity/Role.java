package com.example.practice_mobilelele.model.entity;

import com.example.practice_mobilelele.model.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;

    public Role() {
    }

    @Enumerated
    public RoleEnum getRole() {
        return name;
    }

    public void setRole(RoleEnum name) {
        this.name = name;
    }
}
