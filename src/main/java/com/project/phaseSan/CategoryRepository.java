package com.project.phaseSan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByCategoryName(String name);
    @Modifying
    @Transactional
    @Query("delete from Category cat where cat.categoryName= ?1")
    int deleteCategoryByCategoryName(String categoryName);
}
