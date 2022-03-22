package com.example.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.sms.Repository.StudentRepository;
import com.example.sms.entity.Student;

@SpringBootApplication
public class StudentManagementSystem2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystem2Application.class, args);
	}


	@Autowired
	private StudentRepository studentRepository;



	@Override
	public void run(String... args) throws Exception {

		Student student1 = new Student("Shafiq","islam","shafiq@gmail.com");
		studentRepository.save(student1);
		Student student2 = new Student("RanaAsif","Khandakar","rana@grz.com");
		studentRepository.save(student2);
	}



}
