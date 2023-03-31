package com.example.labbooking.service;

import com.example.labbooking.model.Lecturer;
import com.example.labbooking.model.SecurityOfficer;
import com.example.labbooking.repository.SecurityOfficerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityOfficerService {

    @Autowired
    SecurityOfficerRepo securityOfficerRepo;

    public List<SecurityOfficer> getAllOfficers(){

       return securityOfficerRepo.findAll();

    }

    public void saveOfficer(SecurityOfficer securityOfficer){
         securityOfficerRepo.save(securityOfficer);
    }

    public SecurityOfficer getOfficerById(Long id){

        Optional<SecurityOfficer> optional = securityOfficerRepo.findById(id);

        SecurityOfficer officer = null;

        if(optional.isPresent()){
            officer = optional.get();
        }else{
            throw new RuntimeException("Officer not found for id : " + id);
        }
        return officer;
    }

    public void deleteOfficerById(Long id) {

        this.securityOfficerRepo.deleteById(id);
    }


}
