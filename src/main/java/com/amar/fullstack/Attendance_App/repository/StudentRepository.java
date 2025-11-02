package com.amar.fullstack.Attendance_App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amar.fullstack.Attendance_App.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Student findByEmail(String email);
	
	Student findByRegistrationNumber(String registerationNumber);
	
	Student findByPhoneNumber(String PhoneNumber);
	
	List<Student> findByRole(Student.Role role);
}
