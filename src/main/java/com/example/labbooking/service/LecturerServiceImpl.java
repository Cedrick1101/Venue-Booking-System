package com.example.labbooking.service;

import com.example.labbooking.model.Lecturer;
import com.example.labbooking.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements  LecturerService{

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    @Override
    public void saveLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    @Override
    public Optional<Lecturer> getLecturer(Long id) {
        return  lecturerRepository.findById(id);
    }

    @Override
    public Lecturer getLecturerById(Long id) {

        Optional<Lecturer> optional = lecturerRepository.findById(id);

        Lecturer lecturer = null;

        if(optional.isPresent()){
            lecturer = optional.get();
        }else{
            throw new RuntimeException("Lecturer not found for id : " + id);
        }
        return lecturer;
    }

    @Override
    public void deleteLecturerById(Long id) {

        this.lecturerRepository.deleteById(id);
    }

}
