package com.project.phaseSan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    private int userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userPhone")
    private String userPhone;
    @Column(name = "userEmail")
    private String userEmail;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userName", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("userName")
    private List<OrderReport> orderReportList = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
