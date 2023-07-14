package com.project.phaseSan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {
    List<Admin> findByAdminName(String name);
    @Modifying
    @Transactional
    @Query("update Admin ad set ad.adminPassword = ?1 where ad.adminName = ?2")
    int updateAdminByAdminName(String newPassword, String adminName);
}
