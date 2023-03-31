package com.example.labbooking.service;

import com.example.labbooking.model.Lecturer;

import java.util.List;
import java.util.Optional;

public interface  LecturerService {

    List<Lecturer> getAllLecturers ();

    void saveLecturer(Lecturer lecturer);

    Optional<Lecturer> getLecturer(Long id);

    Lecturer getLecturerById(Long id);

    void deleteLecturerById(Long id);
}
