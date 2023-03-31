package com.example.labbooking.controller;

import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.ClassRoom;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.repository.BookingRepository;
import com.example.labbooking.repository.ClassRoomRepository;
import com.example.labbooking.repository.LecturerRepository;
import com.example.labbooking.service.BookingService;
import com.example.labbooking.service.ClassRoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private LecturerRepository lecturerService;


    @GetMapping("/lecturer")
    public String viewLecturerPage(Model model){
        List<ClassRoom> classRoomList = classRoomService.getAllClassRooms();
        model.addAttribute("rooms", classRoomList);


        return "lecturerpage";
    }

    @GetMapping("/officer_class")
    public String viewClassroomsByOfficer(Model model){
        List<ClassRoom> classRoomList = classRoomService.getAllClassRooms();
        model.addAttribute("rooms", classRoomList);


        return "officer_classrooms";
    }

    @GetMapping("/officer_bookings")
    public String viewAllBookingsByOfficer(Model model){

        List<BookingRoom> allBookings = bookingService.viewAllBookingsByOfficer();
        model.addAttribute("bookings", allBookings);


        return "officer_bookings";
    }






    @PostMapping("/saveBooking")
    public String saveBooking(String bookingDate, String startTime, String endTime, HttpSession session, Model model) throws ParseException {


        // Parse the input date and times
        Date date= new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        String start1 = start.toString();
        String end1 = end.toString();

        // Check if there are any existing bookings for the given date and time range
        List<BookingRoom> existingBookings = bookingRepository.findByBookingDateAndStarTimeBetween(date, start1, end1);

        if (!existingBookings.isEmpty()) {
            // There is already a booking for the given date and time range
            model.addAttribute("errorMessage", "There is already a booking at that time.");
            return "bookingErrorPage";
        }

        // Create a new BookingRoom object
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setBookingDate(date);
        bookingRoom.setStarTime(String.valueOf(start));
        bookingRoom.setEndTime(String.valueOf(end));



        long lecturerId= (Long)session.getAttribute("lecturerId");
        long roomId= (Long)session.getAttribute("roomId");

        Lecturer lecturer = lecturerRepository.findById(lecturerId).get();
        ClassRoom classRoom = classRoomRepository.findById(roomId).get();

        bookingRoom.setLecturer(lecturer);
        bookingRoom.setClassroom(classRoom);

        bookingRepository.save(bookingRoom);

        return "redirect:/bookingresult";


    }



    @GetMapping("/mybookings")
    public String getBookingsByLecturer(Model model, HttpSession session)
    {

        long id = (Long) session.getAttribute("lecturerId");


            List<BookingRoom> bookingRoomList =  bookingService.getBookingsByLecturer(id);
            model.addAttribute("myBookings",bookingRoomList );

            if(bookingRoomList.isEmpty()){
                return "emptybookings";
            }

        return "mybookingspage";
    }

    @GetMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable("id") Long id)
    {

        bookingService.deleBooking(id);

        return "redirect:/mybookings";
    }
    @GetMapping("/todayBookings/{id}")
    public String getBookingsOfToday(@PathVariable("id") Long id, Model model,ClassRoom classRoom){


        List<BookingRoom> todayBookings = bookingService.getAllBookingsOfToday(classRoom);
        model.addAttribute("todayBookings", todayBookings );

        return "todayBookings";

    }

    @GetMapping("/lecturerbookingsperday")
    public String getLecturerBookingsPerDay(Model model, HttpSession session){


          Long id = (Long) session.getAttribute("lecturerId");

        Lecturer lecturer = lecturerService.findById(id).get();


        List<BookingRoom> lecturerDailyBookings = bookingService.getAllBookingsOfTodayOfLecturer(lecturer);
        model.addAttribute("lecturerbookingsperday", lecturerDailyBookings );


        if(lecturerDailyBookings.isEmpty()){

            return "lecturer_emplty_dailybooking";
        }




        return "lecturerbookingsperday";

    }



    @GetMapping("/dailyBookings")
    public String getBookingsOfToday(Model model){


        List<BookingRoom> todayBookings = bookingService.getDailyBookingsForOfficer();
        model.addAttribute("dailyBookings", todayBookings );

        if(todayBookings.isEmpty()){

            return "officer_emplty_dailybooking";
        }

        return "todayBookingsforofficer";

    }

    @GetMapping("/dailyBookings_report")
    public String getBookingsOfTodayReport(Model model){


        List<BookingRoom> todayBookings = bookingService.getDailyBookingsForOfficer();
        model.addAttribute("dailyBookings", todayBookings );



        return "todayBookingsforofficer_report";

    }




}
