package com.example.labbooking.generator;


import com.example.labbooking.document.DocumentGenerator;
import com.example.labbooking.document.DocumentGenerator2;
import com.example.labbooking.mapper.DataMapper;
import com.example.labbooking.mapper.DataMapper2;
import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.service.BookingService;
import com.example.labbooking.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Controller
public class LecturerListDocument {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    BookingService bookingService;

    @Autowired
    private DataMapper2 dataMapper2;

    @Autowired
    private DocumentGenerator2 documentGenerator2;

    @PostMapping(value = "/lecturerlistreport")
    public String generateDocument(@RequestBody List<BookingRoom> bookingList){

        Context dataContext = dataMapper2.setData(bookingList);

        String finalHtml = springTemplateEngine.process("todayBookingsforofficer", dataContext);

        String convertedFile =  documentGenerator2.htmlToPdf(finalHtml);


        return convertedFile;

    }


    @GetMapping(value = "/lecturerlistreport")
    public String getDocument(){

        List<BookingRoom> bookingList = bookingService.getDailyBookingsForOfficer();

        Context dataContext = dataMapper2.setData(bookingList);

        String finalHtml = springTemplateEngine.process("todayBookingsforofficer_report", dataContext);

        documentGenerator2.htmlToPdf(finalHtml);

        return "officerreport";
    }




}
