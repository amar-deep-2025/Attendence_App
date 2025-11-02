package com.amar.fullstack.Attendance_App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amar.fullstack.Attendance_App.entity.Attendance;
import com.amar.fullstack.Attendance_App.entity.Student;

@Repository
public interface AttendanceRepositiry extends JpaRepository<Attendance, Long>{
	
	List<Attendance> findByStudent(Student student);
	
	List<Attendance> findByEmailId(String emailId);
}
