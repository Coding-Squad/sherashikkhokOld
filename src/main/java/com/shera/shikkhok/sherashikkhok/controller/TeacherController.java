package com.shera.shikkhok.sherashikkhok.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shera.shikkhok.sherashikkhok.model.Teacher;
import com.shera.shikkhok.sherashikkhok.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/teachersList", method = RequestMethod.GET)
	public ModelAndView allTeachersList() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Teacher> teachersList = teacherService.findAll();
		
		if (teachersList!=null) {
			modelAndView.addObject(teachersList);
			modelAndView.setViewName("teachersList");
		}
		
		System.out.println("modeland view:.........................."+"         "+modelAndView);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/teacherRegistration", method = RequestMethod.GET)
	public ModelAndView teacherRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacher = new Teacher();
		modelAndView.addObject("teacher", teacher);
		modelAndView.setViewName("teacherRegistration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/teacherRegistration", method = RequestMethod.POST)
	public ModelAndView createNewTeacher(@Valid Teacher teacher, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacherExists = teacherService.findteacherByEmail(teacher.getTeacherEmail());
		if (teacherExists != null) {
			bindingResult
					.rejectValue("email", "error.teacher",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("teacherRegistration");
		} else {
			teacherService.saveTeacher(teacher);
			modelAndView.addObject("successMessage", "Teacher has been added successfully");
			modelAndView.addObject("teacher", new Teacher());
			modelAndView.setViewName("teacherRegistration");
			
		}
		return modelAndView;
	}
	
}
/*
modelAndView.addObject("teacherName", "Welcome"+" "+ teacher.getName()+ " "+ teacher.getInstituteName()+ " "+
		teacher.getFacebookId()+" "+teacher.getTeacherEmail()+" "+teacher.getAboutTeacher());*/