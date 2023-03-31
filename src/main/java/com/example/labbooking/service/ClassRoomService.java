package com.example.labbooking.service;

import com.example.labbooking.model.ClassRoom;
import com.example.labbooking.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    public void  saveClassroomToDB(MultipartFile file, String roomNum, String buildingName, int totalCapacity,String description)
    {
         ClassRoom classRoom = new ClassRoom();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            classRoom.setImage( Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        classRoom.setDescription(description);

        classRoom.setRoomNum(roomNum);
        classRoom.setBuildingName(buildingName);
        classRoom.setTotalCapacity(totalCapacity);
        classRoom.setDescription(description);

        classRoomRepository.save(classRoom);
    }
    public List<ClassRoom> getAllClassRooms()
    {
        return classRoomRepository.findAll();
    }
    public void deleteClassRoomById(Long id)
    {
        classRoomRepository.deleteById(id);
    }
    public void changeRoomNum(Long id ,String roomNum)
    {
        ClassRoom classRoom = new ClassRoom();
        classRoom = classRoomRepository.findById(id).get();
        classRoom.setRoomNum(roomNum);
        classRoomRepository.save(classRoom);
    }

    public void changeBuildingName(Long id ,String buildingName)
    {
        ClassRoom classRoom = new ClassRoom();
        classRoom = classRoomRepository.findById(id).get();
        classRoom.setBuildingName(buildingName);
        classRoomRepository.save(classRoom);
    }
    public void changeTotalCapacity(Long id ,int totalCapacity)
    {
        ClassRoom classRoom = new ClassRoom();
        classRoom = classRoomRepository.findById(id).get();
        classRoom.setTotalCapacity(totalCapacity);
        classRoomRepository.save(classRoom);
    }

    public void changeDescription(Long id ,String description)
    {
        ClassRoom classRoom = new ClassRoom();
        classRoom = classRoomRepository.findById(id).get();
        classRoom.setDescription(description);
        classRoomRepository.save(classRoom);
    }



}
