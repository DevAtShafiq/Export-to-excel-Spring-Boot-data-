package com.example.sms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
