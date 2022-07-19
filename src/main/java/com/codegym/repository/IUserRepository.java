package com.codegym.repository;

import com.codegym.model.entity.Field;
import com.codegym.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    //tim kiem co ton tai trong DB khong
    Optional<User> findByUsername(String name);

    //kt xem user da co torng DB chua khi tao du lieu
    Boolean existsByUsername(String name);

    //kt xem email da co torng DB chua khi tao du lieu
    Boolean existsByEmail(String email);
    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}
