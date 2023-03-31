package com.example.labbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/lecturerwelcomepage")
    public String homePage(){
        return "lecturerwelcomepage";
    }

    @GetMapping("/bookingresult")
    public String bookingResult(){

        return "bookingresult";
    }
}
