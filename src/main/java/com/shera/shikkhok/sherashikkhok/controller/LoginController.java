package com.shera.shikkhok.sherashikkhok.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shera.shikkhok.sherashikkhok.model.Teacher;
import com.shera.shikkhok.sherashikkhok.model.User;
import com.shera.shikkhok.sherashikkhok.service.TeacherService;
import com.shera.shikkhok.sherashikkhok.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getFacebookId()+ " " + user.getInstituteName()
		+ " "+ user.getMobileNumber() + " " +user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	
	/*@RequestMapping(value="/teacherRegistration", method = RequestMethod.GET)
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
	}*/
	
}
