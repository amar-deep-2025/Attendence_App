package com.amar.fullstack.Attendance_App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amar.fullstack.Attendance_App.entity.Student;
import com.amar.fullstack.Attendance_App.entity.Student.Role;
import com.amar.fullstack.Attendance_App.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private final StudentRepository studentRepository ;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository=studentRepository;
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	
	public Student saveStudent(Student student) {
		
		Student existingEmail=studentRepository.findByEmail(student.getEmail());
		Student existingRegisterationNumber=studentRepository.findByRegistrationNumber(student.getRegistrationNumber());
		Student existingPhoneNumber=studentRepository.findByPhoneNumber(student.getPhoneNumber());
		if (existingEmail!=null) {
			throw new RuntimeException("Email Already Exists :"+student.getEmail());
		}
		if (existingRegisterationNumber!=null) {
			throw new RuntimeException("Registeration Number allready exist :"+student.getRegistrationNumber());
		}
		if (existingPhoneNumber!=null) {
			throw new RuntimeException("Phoner Number allready exist :"+student.getPhoneNumber());
		}
		return studentRepository.save(student);
	}
	
	public Student getById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Student not found with this id "+id));
	}
	
	public Student getByEmail(String email) {
		Student student=studentRepository.findByEmail(email);
		if (student==null) {
			throw new RuntimeException("Student not found with this email "+email);
		}
		return student;
	}
	
	
	public Student updateStudent(Long id, Student updatedData) {
		Student existing=getById(id);
		if (existing==null) {
			throw new RuntimeException("Student not found with this id :"+id);
		}
		//update fields
		existing.setName(updatedData.getName());
		existing.setEmail(updatedData.getEmail());
		existing.setPhoneNumber(updatedData.getPhoneNumber());
		existing.setRole(updatedData.getRole());
		existing.setPassword(updatedData.getPassword());
		return studentRepository.save(existing);
	}
	
	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new RuntimeException("Can not delete. Student not found with this id "+id);
		}
		studentRepository.deleteById(id);
	}
	
	public List<Student> getStudentsByRole(Student.Role role){
		if (role==null) {
			throw new RuntimeException("Role cannot be null");
		}
		
		List<Student> students=studentRepository.findByRole(role);
		if (students.isEmpty()) {
			throw new RuntimeException("No student found with role : "+role);
		}
		return students;
	}

	
}
