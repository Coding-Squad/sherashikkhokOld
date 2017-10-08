package com.shera.shikkhok.sherashikkhok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shera.shikkhok.sherashikkhok.model.Teacher;
import com.shera.shikkhok.sherashikkhok.repository.TeacherRepository;
import com.shera.shikkhok.sherashikkhok.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherRepository teacherRepository;	

	@Override
	public Teacher findteacherByEmail(String email) {
		return teacherRepository.findByTeacherEmail(email) ;
	}
	
	

	@Override
	public Teacher findTeacherByName(String name) {
		// TODO Auto-generated method stub
		return teacherRepository.findByName(name);
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		
		teacherRepository.save(teacher);
		
	}


	@Override
	public List<Teacher> findAll() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll();
	}

}
