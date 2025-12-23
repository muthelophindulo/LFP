package com.LFP.service;

import com.LFP.model.admin;
import com.LFP.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //get all the admins
    public List<admin> getAdmins(){
        return adminRepository.findAll();
    }

    //get admin by AdminNo
    public admin getAdminByAdminNo(String AdminNo){
        return adminRepository.findByadminNO(AdminNo);
    }

    //find by name
    public admin getAdminByName(String name){
        return adminRepository.findByname(name);
    }

    //add the admin
    public admin addAdmin(admin admin){
        return adminRepository.save(admin);
    }

    //delete admin
    public void deleteAdmin(admin admin){
        adminRepository.delete(admin);
    }


}
