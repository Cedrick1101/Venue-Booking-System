package com.example.labbooking.controller;


import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.model.SecurityOfficer;
import com.example.labbooking.repository.BookingRepository;
import com.example.labbooking.service.BookingService;
import com.example.labbooking.service.LecturerService;
import com.example.labbooking.service.SecurityOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class SecurityOfficerController {

    @Autowired
    SecurityOfficerService securityOfficerService;

    @GetMapping("/officerlist")
    public String getAllOfficers(Model model){

            List<SecurityOfficer> officerList = securityOfficerService.getAllOfficers();

            model.addAttribute("officers",officerList );

            return  "officerlist";
    }

    @GetMapping("/showNewOfficerForm")
    public String showNewOfficerForm(Model model){

        // create model attribute to bind form data
        SecurityOfficer officer = new SecurityOfficer();
        model.addAttribute("officer", officer );

        return "new_officer";
    }







    @PostMapping("/saveOfficer")
    public String saveOfficer(@ModelAttribute("officer") SecurityOfficer officer){
        //save  officer
         securityOfficerService.saveOfficer(officer);


        return "redirect:/officerlist";
    }

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/lecturerlistforofficers")
    public String viewLecturers(Model model){

        model.addAttribute("lecturerList", lecturerService.getAllLecturers());
        return "lecturerlist";

    }

    @GetMapping("/backofficerpage")
    public String goBackToOfficerWelcomePage(){

        return "redirect:/officerpage";

    }

    @GetMapping("/officerpage")
    public String goBackToOfficerWelcomePage2(){

        return "officerpage";

    }

    @GetMapping("/showFormForOfficerUpdate/{id}")
    public String showFormForOfficerUpdate(@PathVariable(value = "id") long id, Model model){

        SecurityOfficer officer = securityOfficerService.getOfficerById(id);

        model.addAttribute("officer", officer);

        return  "update_officer";

    }

    @GetMapping("/deleteOfficer/{id}")
    public String deleteOfficer(@PathVariable (value = "id") long id){

        this.securityOfficerService.deleteOfficerById(id);

        return "redirect:/officerlist";
    }

}
