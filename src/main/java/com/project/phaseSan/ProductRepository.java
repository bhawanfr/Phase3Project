package com.project.phaseSan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductName(String productName);

    @Query("select new com.project.phaseSan.ProductResult(p.productId, p.productName, p.productPrice, c.categoryName, c.categoryDescription) from Product p inner join Category c on c.categoryId= p.category.categoryId")
    List<ProductResult> findAllProduct();

    @Query("select new com.project.phaseSan.ProductResult(p.productId, p.productName, p.productPrice, c.categoryName, c.categoryDescription) from Product p inner join Category c on c.categoryId= p.category.categoryId where p.category.categoryId = ?1")
    List<ProductResult> findAllProductByCategory(int categoryId);

    @Modifying
    @Transactional
    @Query("update Product p set p.productName= ?1 where p.productId =?2")
    int updateProductNameByProductId(String productName, int productId);

    @Modifying
    @Transactional
    @Query("update Product p set p.productPrice= ?1 where p.productId =?2")
    int updateProductPriceByProductId(int Price, int productId);

    @Modifying
    @Transactional
    @Query("update Product p set p.category.categoryId= ?1 where p.productId =?2")
    int updateCategoryByProductId(int categoryId, int productId);

    @Modifying
    @Transactional
    @Query("delete from Product p where p.productName= ?1")
    int deleteByProductName(String categoryName);

}

//{
//        "productId" : 1,
//        "productName" : "Nike Jordan 1 Aqua Blue",
//        "productPrice" : 25000,
//        "category" : {
//        "categoryId" : 1,
//        "categoryName" : "Sport Shoes",
//        "categoryDescription" : "Durable and Highly Reliable"
//        }
//
//        }