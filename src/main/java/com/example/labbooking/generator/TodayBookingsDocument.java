package com.example.labbooking.generator;


import com.example.labbooking.document.DocumentGenerator;
import com.example.labbooking.mapper.DataMapper;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Controller
public class TodayBookingsDocument {


    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DocumentGenerator documentGenerator;

    @PostMapping(value = "/printlist")
    public String generateDocument(@RequestBody List<Lecturer> lecturerList){

        Context dataContext = dataMapper.setData(lecturerList);

        String finalHtml = springTemplateEngine.process("lecturerlist_report", dataContext);

         String convertedFile =  documentGenerator.htmlToPdf(finalHtml);


        return convertedFile;

    }


    @GetMapping(value = "/printlist")
    public String getDocument(){

        List<Lecturer> lecturerList = lecturerService.getAllLecturers();

        Context dataContext = dataMapper.setData(lecturerList);

        String finalHtml = springTemplateEngine.process("lecturerlist_report", dataContext);

           documentGenerator.htmlToPdf(finalHtml);

        return "officerreport";
    }

















}
