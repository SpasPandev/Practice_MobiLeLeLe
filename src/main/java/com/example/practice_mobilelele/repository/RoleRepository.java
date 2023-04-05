package com.example.practice_mobilelele.repository;

import com.example.practice_mobilelele.model.entity.Role;
import com.example.practice_mobilelele.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.role = ?1")
    Role findByName(RoleEnum roleEnum);

}
