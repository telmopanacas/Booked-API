package com.telmopanacas.bookedapi.Security.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByDisplayName(String displayName);
    Optional<User> findByEmail(String email);
}
