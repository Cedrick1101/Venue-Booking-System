package com.example.labbooking.mapper;

import com.example.labbooking.model.Lecturer;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataMapper {

    public Context setData(List<Lecturer> lecturerlist){

        Context context = new Context();

        Map<String,Object> data  = new HashMap<>();

        data.put("lecturerlist", lecturerlist);

        context.setVariables(data);


         return context;

    }
}
