package com.LFP.model;

import jakarta.persistence.*;


@Entity
@Table(name ="users")
public class admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String adminNO;

    @Column(unique = true,nullable = false)
    private String password;

    public admin(){

    }

    public admin(Long id, String name, String adminNO,String password) {
        this.id = id;
        this.name = name;
        this.adminNO = adminNO;
        this.password = password;
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
        return adminNO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminNO(String adminNO) {
        this.adminNO = adminNO;
    }
}
