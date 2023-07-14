package com.project.phaseSan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUserName(String userName);
}
