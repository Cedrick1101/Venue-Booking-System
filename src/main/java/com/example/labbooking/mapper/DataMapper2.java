package com.example.labbooking.mapper;

import com.example.labbooking.model.BookingRoom;
import com.example.labbooking.model.Lecturer;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataMapper2 {

    public Context setData(List<BookingRoom> bookingList){

        Context context = new Context();

        Map<String,Object> data  = new HashMap<>();

        data.put("dailyBookings", bookingList);

        context.setVariables(data);


        return context;

    }


}
