package com.project.phaseSan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "OrderReport")
public class OrderReport {
    @Id
    private int purchaseOrderId;
    @Column(name = "purchaseOrderDate")
    private java.sql.Date purchaseOrderDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonIgnoreProperties("orderReportList")
    private User userName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "purchaseOrderProductMapping",
            joinColumns = @JoinColumn(name = "purchaseOrderId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private List<Product> purchaseOrderProductMapping;

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public boolean addProductToOrder(Product newEntry)
    {
        return this.purchaseOrderProductMapping.add(newEntry);
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public void setPurchaseOrderProductMapping(List<Product> purchaseOrderProductMapping) {
        this.purchaseOrderProductMapping = purchaseOrderProductMapping;
    }

    public List<Product> getPurchaseOrderProductMapping() {
        return purchaseOrderProductMapping;
    }
}
