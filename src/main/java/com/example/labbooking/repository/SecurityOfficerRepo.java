package com.example.labbooking.repository;

import com.example.labbooking.model.SecurityOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityOfficerRepo extends JpaRepository<SecurityOfficer, Long> {

    SecurityOfficer findSecurityOfficerByEmailAndPassword(String email,String password);

    SecurityOfficer getOfficerById(Long id);
}
