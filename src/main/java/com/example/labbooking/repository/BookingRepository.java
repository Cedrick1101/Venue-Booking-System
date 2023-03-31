package com.example.labbooking.repository;

import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.ClassRoom;
import com.example.labbooking.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingRoom,Long> {

    List<BookingRoom> findByLecturerId(Long id);

    List<BookingRoom> findAll();

    List<BookingRoom> findByBookingDateAndClassroom(Date date, ClassRoom classRoom);

    List<BookingRoom> findByBookingDate(Date today);

    List<BookingRoom> findByBookingDateAndLecturer(Date today, Lecturer lecturer);

    List<BookingRoom> findByBookingDateAndStarTimeBetween(Date bookingDate, String startTime, String endTime);



}
