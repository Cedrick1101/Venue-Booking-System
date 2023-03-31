package com.example.labbooking.repository;

import com.example.labbooking.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Lecturer findLecturerByEmailAndPassword(String email, String password);
}
