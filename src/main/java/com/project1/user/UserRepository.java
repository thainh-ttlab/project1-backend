package com.project1.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u " +
            "where u.name LIKE %?1% " +
            "or u.address like %?1% " +
            "or u.phone like %?1% " +
            "or u.email like %?1%"
    )
    Page<User> findUser(
            String keyword,
            Pageable pageable
    );
}
