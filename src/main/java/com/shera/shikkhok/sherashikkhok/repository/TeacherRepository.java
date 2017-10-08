package com.shera.shikkhok.sherashikkhok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shera.shikkhok.sherashikkhok.model.Teacher;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	Teacher findByTeacherEmail(String email);
	
	Teacher findByName(String name);

}
