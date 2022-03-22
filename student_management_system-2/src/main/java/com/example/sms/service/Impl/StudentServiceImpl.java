package com.example.sms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.sms.Repository.StudentRepository;
import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;




	public List<Student>getAllStudents(){

		return studentRepository.findAll();
	}


	
	public Student saveStudent(Student student) {
		
		return studentRepository.save(student);
	}


	
	public Student getStudentById(Long id) {
		
		return studentRepository.findById(id).get();
	}


	
	public Student updateStudent (Student student) {
		
		return studentRepository.save(student);
	}



	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		
		studentRepository.deleteById(id);
	}



	
	



	
	
	
}
