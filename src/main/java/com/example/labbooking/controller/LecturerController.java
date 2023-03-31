package com.example.labbooking.controller;

import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.service.LecturerService;
import com.example.labbooking.service.LecturerServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/lecturerlist")
    public String viewLecturers(Model model){

          model.addAttribute("lecturerList", lecturerService.getAllLecturers());
          return "lecturerlist";

    }





    @GetMapping("/lecturerlist_admin")
    public String viewLecturersByOfficer(Model model){

        model.addAttribute("lecturerList", lecturerService.getAllLecturers());
        return "lecturerlist_admin";

    }

    @GetMapping("/homelecturer")
    public String homeLecturer(Model model){

        return "lecturerwelcomepage";

    }



      @GetMapping("/showNewLecturerForm")
    public String showNewLecturerForm(Model model){

         // create model attribute to bind form data
        Lecturer lecturer = new Lecturer();
          model.addAttribute("lecturer", lecturer );

          return "new_lecturer";
    }

    @GetMapping("/adminpage")
    public String showAdminPage(){

        // create model attribute to bind form data

        return "adminpage";
    }



    @PostMapping("/saveLecturer")
    public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer){
        //save  lecturer
        lecturerService.saveLecturer(lecturer);


        return "redirect:/lecturerlist_admin";
    }

    @GetMapping("/goToBookPage/{id}")
    public String goToBookPage(@PathVariable("id") Long id, HttpSession session, Model model){
        session.setAttribute("roomId",id);


        return "/bookclass";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){

        Lecturer lecturer = lecturerService.getLecturerById(id);

        model.addAttribute("lecturer", lecturer);

        return  "update_lecturer";

    }




    @GetMapping("/deleteLecturer/{id}")
  public String deleteLecturer(@PathVariable (value = "id") long id){

        this.lecturerService.deleteLecturerById(id);

        return "redirect:/lecturerlist_admin";
  }


}
