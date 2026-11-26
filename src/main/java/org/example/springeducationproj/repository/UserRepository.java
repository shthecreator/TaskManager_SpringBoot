package org.example.springeducationproj.repository;

import org.example.springeducationproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}
