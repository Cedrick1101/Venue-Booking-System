package com.example.labbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/manage_classroom")
    public String goToManageClassroom(){
        return "manage_classroom";
    }


    @GetMapping("/manage_lecturer")
    public String goToManageLecturer(){
        return "manage_lecturer";
    }

    @GetMapping("/manage_officer")
    public String goToManageOfficer(){
        return "manage_officer";
    }

    @GetMapping("/manage_booking")
    public String goToManageBooking(){
        return "manage_booking";
    }

}
