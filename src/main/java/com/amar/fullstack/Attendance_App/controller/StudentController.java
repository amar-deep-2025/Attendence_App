package com.amar.fullstack.Attendance_App.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amar.fullstack.Attendance_App.entity.Student;
import com.amar.fullstack.Attendance_App.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final StudentService stuService;

	public StudentController(StudentService stuService) {
		this.stuService = stuService;
	}

	@GetMapping
	public String listStudents(Model model) {
		List<Student> students = stuService.getAllStudents();
		model.addAttribute("students", students);
		return "students/list";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("student", new Student());
		return "students/add";
	}

	@PostMapping("/add")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {

		try {
			if (result.hasErrors()) {
				System.out.println("validation errors: " + result.getAllErrors());
				return "students/add";
			}

			stuService.saveStudent(student);
			redirectAttributes.addFlashAttribute("message", "Student added successfully!");
			model.addAttribute("student", new Student());
			return "redirect:/students";
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "students/add";
		}

	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Student student = stuService.getById(id);
		if (student == null) {
			model.addAttribute("error", "Student not found");
			return "redirect:/students";
		}
		model.addAttribute("student", student);
		return "students/edit";
	}

	@PostMapping("/edit/{id}")
	public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Student updatedStudent,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "student/edit";
		}

		stuService.updateStudent(id, updatedStudent);
		redirectAttributes.addFlashAttribute("message", "Student updated successfully!");

		return "redirect:/students";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			stuService.deleteStudent(id);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		redirectAttributes.addFlashAttribute("message","Student deleted successfully");

		return "redirect:/students";
	}

	@GetMapping("{id}")
	public String viewStudent(@PathVariable Long id, Model model) {
		Student student = stuService.getById(id);
		if (student == null) {
			model.addAttribute("error", "Student not found");
			return "redirect:/students";
		}
		model.addAttribute("student", student);
		return "student/view";
	}

}
