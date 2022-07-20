package com.codegym.repository;

import com.codegym.constant.Constant;

import com.codegym.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Constant.RoleName name);
}
