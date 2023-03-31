package com.example.labbooking.controller;


import com.example.labbooking.model.ClassRoom;
import com.example.labbooking.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("/classroomlist")
    public String showExampleView(Model model)
    {
        List<ClassRoom> classRoomList = classRoomService.getAllClassRooms();
        model.addAttribute("rooms", classRoomList);



        return "/classroomlist.html";
    }
    @GetMapping("/showaddclassroom")
    public String showAddClassroom()
    {

        return "/addclass.html";
    }

    @PostMapping("/saveClass")
    public String saveClassroom(@RequestParam("file") MultipartFile file,
                              @RequestParam String roomNum,
                              @RequestParam String buildingName,
                              @RequestParam int totalCapacity,
                              @RequestParam String description)
    {
        classRoomService.saveClassroomToDB(file, roomNum, buildingName, totalCapacity, description);
        return "redirect:/classroomlist";
    }

    @GetMapping("/deleteClass/{id}")
    public String deleteClassroom(@PathVariable("id") Long id)
    {

        classRoomService.deleteClassRoomById(id);
        return "redirect:/classroomlist.html";
    }

    @PostMapping("/changeRoomNum")
    public String changeClassRoomNum(@RequestParam("id") Long id,
                              @RequestParam("newClassroomNum") String roomNum)
    {
        classRoomService.changeRoomNum(id,roomNum);
        return "redirect:/classroomlist.html";
    }

    @PostMapping("/changeBuildingN")
    public String changeBuildingName(@RequestParam("id") Long id,
                                     @RequestParam("newBuildingName") String buildingName)
    {
        classRoomService.changeBuildingName(id, buildingName);
        return "redirect:/classroomlist.html";
    }

    @PostMapping("/changeTotalC")
    public String changeTotalCapacity(@RequestParam("id") Long id,
                                     @RequestParam("newTotalCapacity") int totalCapacity)
    {
        classRoomService.changeTotalCapacity(id, totalCapacity);
        return "redirect:/classroomlist.html";
    }

    @PostMapping("/changeDesc")
    public String changeDescription(@RequestParam("id") Long id,
                                     @RequestParam("newDescription") String description)
    {
        classRoomService.changeDescription(id, description);
        return "redirect:/classroomlist.html";
    }


}
