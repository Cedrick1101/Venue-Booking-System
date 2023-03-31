package com.example.labbooking.controller;


import com.example.labbooking.model.Admin;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.model.SecurityOfficer;
import com.example.labbooking.repository.AdminRepo;
import com.example.labbooking.repository.LecturerRepository;
import com.example.labbooking.repository.SecurityOfficerRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    SecurityOfficerRepo securityOfficerRepo;
    @GetMapping("/")
    public  String welcomePage(){
        return "welcome";
    }

    @PostMapping("/login")
    public String login(String email, String password, String selectedUser, HttpSession session){


        if (selectedUser.equals("admin")){
            Admin admin =adminRepo.findAdminByEmailAndPassword(email,password);

            if(admin==null){
                return  "errorpage";
            }

            session.setAttribute("user", admin);
            session.setAttribute("adminId",admin.getId());



            return  "adminpage";
        }
        if (selectedUser.equals("lecturer")){

            Lecturer lecturer = lecturerRepository.findLecturerByEmailAndPassword(email,password);

            if(lecturer==null){
                return  "errorpage";
            }

            session.setAttribute("user", lecturer);
            session.setAttribute("lecturerId",lecturer.getId());

            return "lecturerwelcomepage";

        }
        if (selectedUser.equals("officer")){

            SecurityOfficer officer = securityOfficerRepo.findSecurityOfficerByEmailAndPassword(email,password);
            if(officer==null){
                return  "errorpage";
            }
            session.setAttribute("user", officer);
            session.setAttribute("officerId",officer.getId());

            return "officerpage";
        }


     return  null;
    }
}
