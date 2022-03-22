package com.example.sms.controller;
import com.example.sms.Repository.StudentRepository;
import com.example.sms.exportToexcel.StudentExportToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class StudentController {

	@Autowired
	public StudentService studentService;
	@Autowired
	public StudentRepository studentRepository;


	@GetMapping("/students")
	public String listStudent(Model model) {


		// here List is Student type it means in Student class there are certain variable and to receive "getAllStudents"
		// it suppose to have the same nature of the students its getting.

 		List<Student> allStudents =studentService.getAllStudents();
		model.addAttribute("allStudents",allStudents);
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		Student student = new Student();
		model.addAttribute("student",student);
		return "create_student";
	}
	
	
	@PostMapping("/save_students")
	public String saveStudent(@ModelAttribute ("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String ediStudentForm(@PathVariable Long id, Model model) {
		Student studentNeedToEdit = studentService.getStudentById(id);
		model.addAttribute("studentNeedToEdit",studentNeedToEdit);
		return "edit_student";
	}
	
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute ("student") Student student) {
		
	//get student from database by id
Student existingStudent = studentService.getStudentById(id);
existingStudent.setId(id);
	existingStudent.setFirstName(student.getFirstName());
	existingStudent.setLastName(student.getLastName());
	existingStudent.setEmail(student.getEmail());
	studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	
	//handler method to delete student request
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		
		return "redirect:/students";
	}


// handler for exporting to excel

	@GetMapping("users/export")
	public  void exportToExcel(HttpServletResponse response) throws IOException {
response.setContentType("application/octet-stream");
String headerKey = "Content-Disposition";
String headerValue = "attachment; filename=user.xlsx";

response.setHeader(headerKey,headerValue);

List<Student>studentList = studentService.getAllStudents();
		StudentExportToExcel exportToExcel = new StudentExportToExcel(studentList);
		exportToExcel.export(response);


	}

}
