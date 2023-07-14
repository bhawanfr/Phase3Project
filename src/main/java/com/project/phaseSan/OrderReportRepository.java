package com.project.phaseSan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderReportRepository extends JpaRepository<OrderReport, String> {
    @Query("select orre from OrderReport orre inner join fetch orre.purchaseOrderProductMapping p where p.category.categoryId= ?1")
    List<OrderReport> findAllByCategory(int categoryId);

    List<OrderReport> findAllByPurchaseOrderDate(java.sql.Date date);
}
