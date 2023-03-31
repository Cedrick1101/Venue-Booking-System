package com.example.labbooking.repository;

import com.example.labbooking.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository extends JpaRepository <ClassRoom, Long> {
}
