package com.LFP.controller;

import com.LFP.model.AdminResponse;
import com.LFP.model.admin;
import com.LFP.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin/")
public class AdminController {
    AdminService adminService;

    @GetMapping
    public List<AdminResponse> getAllAdmins(){
        return adminService.getAdmins()
                .stream()
                .map(AdminResponse::new)
                .toList();
    }

    @GetMapping("{AdminNo}")
    public AdminResponse getAdminByAdminNo(@PathVariable String AdminNo){
        admin x = adminService.getAdminByAdminNo(AdminNo);
        if(x != null){
            return new AdminResponse(x);
        }
        else
            return null;
    }

    @GetMapping("name/{name}")
    public AdminResponse getAdminByName(@PathVariable String name){
        admin x = adminService.getAdminByName(name);
        if(x != null){
            return new AdminResponse(x);
        }
        else
            return null;
    }

    @DeleteMapping("delete/{admin}")
    public String deleteAdmin(@PathVariable  admin admin){
        adminService.deleteAdmin(admin);
        return "admin " + admin.getAdminNO() + " has been deleted";
    }

    @PostMapping("/add/{admin}")
    public admin addAdmin(@PathVariable admin admin){
        return adminService.addAdmin(admin);
    }
}
