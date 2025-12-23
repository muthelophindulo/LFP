package com.LFP.repository;

import com.LFP.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<admin,Long> {

    admin findByname(String name);

    admin findByadminNO(String adminNo);
}
