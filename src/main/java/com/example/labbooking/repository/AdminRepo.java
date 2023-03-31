package com.example.labbooking.repository;

import com.example.labbooking.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {

    Admin findAdminByEmailAndPassword(String email,String password);
}
