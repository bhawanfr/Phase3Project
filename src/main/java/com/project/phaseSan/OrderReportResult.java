package com.project.phaseSan;

import java.sql.Date;

public class OrderReportResult {
    private String productName;
    private int productPrice;
    private String categoryName;
    private String categoryDescription;
    private java.sql.Date purchaseOrderDate;
    private String userName;
    private String userEmail;

    public OrderReportResult(String productName, int productPrice, String categoryName, String categoryDescription, Date purchaseOrderDate, String userName, String userEmail) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.purchaseOrderDate = purchaseOrderDate;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "OrderReportResult{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", purchaseOrderDate=" + purchaseOrderDate +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}

