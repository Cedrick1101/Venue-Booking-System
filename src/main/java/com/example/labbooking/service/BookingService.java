package com.example.labbooking.service;


import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.ClassRoom;
import com.example.labbooking.model.Lecturer;
import com.example.labbooking.repository.BookingRepository;
import com.example.labbooking.repository.LecturerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;




    public List<BookingRoom> getBookingsByLecturer(long id) {


        return bookingRepository.findByLecturerId(id);


    }

    public List<BookingRoom> getAllBookings(){
        return bookingRepository.findAll();
    }

    public void deleBooking(Long id){
        bookingRepository.deleteById(id);
    }

    public List<BookingRoom> getAllBookingsOfToday(ClassRoom classRoom) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = calendar.getTime();

        return bookingRepository.findByBookingDateAndClassroom(today, classRoom);
    }

    public List<BookingRoom> getAllBookingsOfTodayOfLecturer(Lecturer lecturer) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = calendar.getTime();


        return bookingRepository.findByBookingDateAndLecturer(today, lecturer);
    }

    public List<BookingRoom> getDailyBookingsForOfficer(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = calendar.getTime();
        return bookingRepository.findByBookingDate(today);

    }


     public List<BookingRoom> viewAllBookingsByOfficer(){
        return  bookingRepository.findAll();
     }



}
