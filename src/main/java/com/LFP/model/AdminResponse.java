package com.LFP.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AdminResponse {

    private Long id;

    private String name;

    private String AdminNO;

    public AdminResponse(){}

    public AdminResponse(Long id, String name, String adminNO) {
        this.id = id;
        this.name = name;
        this.AdminNO = adminNO;
    }

    public AdminResponse(admin admin) {
        this.AdminNO = admin.getAdminNO();
        this.name = admin.getName();
        this.id = admin.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminNO() {
        return AdminNO;
    }

    public void setAdminNO(String adminNO) {
        AdminNO = adminNO;
    }
}
