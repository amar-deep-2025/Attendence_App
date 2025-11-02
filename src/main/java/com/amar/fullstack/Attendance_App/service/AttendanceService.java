package com.amar.fullstack.Attendance_App.service;

import java.util.List;

import org.eclipse.jdt.internal.compiler.env.IUpdatableModule.UpdateKind;
import org.springframework.stereotype.Service;

import com.amar.fullstack.Attendance_App.entity.Attendance;
import com.amar.fullstack.Attendance_App.entity.Student;
import com.amar.fullstack.Attendance_App.repository.AttendanceRepositiry;
import com.amar.fullstack.Attendance_App.repository.StudentRepository;

@Service
public class AttendanceService {

	private final AttendanceRepositiry attendanceRepo;

	private final StudentRepository stuRepo;

	public AttendanceService(AttendanceRepositiry attendanceRepo, StudentRepository stuRepo) {

		this.attendanceRepo = attendanceRepo;
		this.stuRepo = stuRepo;
	}

	public List<Attendance> getAllAttendance() {
		List<Attendance> attendaceList = attendanceRepo.findAll();
		if (attendaceList.isEmpty()) {

			throw new RuntimeException("No attendanceRecords found !");
		}
		return attendaceList;
	}

	public Attendance getAttendaceById(Long id) {
		if (id==null || id<=0) {
			throw new RuntimeException("Invalid Attendance id please correct it !");
		}
		return attendanceRepo.findById(id)
				.orElseThrow(()->new RuntimeException("Attendance not Found with this id "));
	}
	
	public Attendance saveAttendance(Attendance attendance, Long studentId) {
		if(attendance==null) {
			throw new IllegalArgumentException("Attendance details can not be null");
		}
		Student student=stuRepo.findById(studentId)
				.orElseThrow(()->new RuntimeException("Student not found with Id"));
		
		attendance.setStudent(student);
		attendance.setEmailId(student.getEmail());
		
		return attendanceRepo.save(attendance);
	}
	
	public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
		
		if (updatedAttendance==null) {
			throw new RuntimeException("Updated attendance can not be a null");
		}
		Attendance existAttendance=getAttendaceById(id);
		
		existAttendance.setDate(updatedAttendance.getDate());
		existAttendance.setLocation(updatedAttendance.getLocation());
		existAttendance.setStartTime(updatedAttendance.getStartTime());
		existAttendance.setEndTime(updatedAttendance.getEndTime());
		existAttendance.setStatus(updatedAttendance.getStatus());
		
		return attendanceRepo.save(existAttendance);
	}
	
	public void deleteAttendance(Long id) {
		if (id==null || id<=0) {
			throw new IllegalArgumentException("Invalid attendance Id Provided");
		}
		
		if (!attendanceRepo.existsById(id)) {
			throw new RuntimeException("Cannot delete. Attendance not found");
		}
		
		attendanceRepo.deleteById(id);
	}
	
	
	public List<Attendance> getAttendanceByStudent(Student student){
		if (student==null) {
			throw new IllegalArgumentException("Student cannot be null");
		}
		
		List<Attendance> records =attendanceRepo.findByStudent(student);
		
		if (records.isEmpty()) {
			throw new RuntimeException("No attendance records foud for student ");
		}
		return records;
	}
	
	public List<Attendance> getAttendanceByEmail(String email){
		if (email==null || email.isBlank()) {
			throw new IllegalArgumentException("email id can not be a null or blank");
		}
		List<Attendance> records=attendanceRepo.findByEmailId(email);
		if (records.isEmpty()) {
			throw new RuntimeException("No attendance records found for email :"+email);
		}
		return records;
	}
	
}
