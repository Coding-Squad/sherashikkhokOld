package com.shera.shikkhok.sherashikkhok.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shera.shikkhok.sherashikkhok.model.Posts;
import com.shera.shikkhok.sherashikkhok.model.Teacher;
import com.shera.shikkhok.sherashikkhok.model.User;
import com.shera.shikkhok.sherashikkhok.service.PostsService;
import com.shera.shikkhok.sherashikkhok.service.TeacherService;
import com.shera.shikkhok.sherashikkhok.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;
	

	@Autowired
	private PostsService postsService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<Teacher> teachersList = teacherService.findAll();

		if (teachersList != null) {
			modelAndView.addObject(teachersList);
			modelAndView.setViewName("index");
		}
		
		/*List<Posts> postsList = postsService.getAllPosts();
		if (postsList != null) {
			modelAndView.addObject(postsList);
			modelAndView.setViewName("postsList");
		}*/

		// modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
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
			bindingResult.rejectValue("email", "error.user",
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

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"<center>Welcome</center> <br></br>" + user.getName() + " " + user.getLastName() + "<br></br> "
						+ "<a style=" + "text-decoration:none;" + "color:white;" + " target=" + "blank" + " href="
						+ user.getFacebookId() + ">Facebook ID Link</a>" + "<br></br> " + user.getInstituteName()
						+ "<br></br> " + user.getMobileNumber() + "<br></br> (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	/*
	 * @RequestMapping(value="/teacherRegistration", method = RequestMethod.GET)
	 * public ModelAndView teacherRegistration(){ ModelAndView modelAndView = new
	 * ModelAndView(); Teacher teacher = new Teacher();
	 * modelAndView.addObject("teacher", teacher);
	 * modelAndView.setViewName("teacherRegistration"); return modelAndView; }
	 * 
	 * @RequestMapping(value = "/teacherRegistration", method = RequestMethod.POST)
	 * public ModelAndView createNewTeacher(@Valid Teacher teacher, BindingResult
	 * bindingResult) { ModelAndView modelAndView = new ModelAndView(); Teacher
	 * teacherExists = teacherService.findteacherByEmail(teacher.getTeacherEmail());
	 * if (teacherExists != null) { bindingResult .rejectValue("email",
	 * "error.teacher",
	 * "There is already a user registered with the email provided"); } if
	 * (bindingResult.hasErrors()) {
	 * modelAndView.setViewName("teacherRegistration"); } else {
	 * teacherService.saveTeacher(teacher); modelAndView.addObject("successMessage",
	 * "Teacher has been added successfully"); modelAndView.addObject("teacher", new
	 * Teacher()); modelAndView.setViewName("teacherRegistration");
	 * 
	 * } return modelAndView; }
	 */

}
