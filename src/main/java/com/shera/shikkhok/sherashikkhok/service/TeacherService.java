package com.shera.shikkhok.sherashikkhok.service;

import java.util.List;

import com.shera.shikkhok.sherashikkhok.model.Teacher;

public interface TeacherService {

	public Teacher findteacherByEmail(String email);
		
	public Teacher findTeacherByName(String name); 
	
	public void saveTeacher(Teacher teacher);
	
	List<Teacher> findAll();
	
}
