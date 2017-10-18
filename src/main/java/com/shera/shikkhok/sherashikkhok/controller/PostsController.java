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
import com.shera.shikkhok.sherashikkhok.model.User;
import com.shera.shikkhok.sherashikkhok.service.PostsService;
import com.shera.shikkhok.sherashikkhok.service.UserService;

@Controller
public class PostsController {

	@Autowired
	private PostsService postService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/postsList", method = RequestMethod.GET)
	public ModelAndView allPostsList() {
		ModelAndView modelAndView = new ModelAndView();

		List<Posts> postsList = postService.getAllPosts();
		if (postsList != null) {
			modelAndView.addObject(postsList);
			modelAndView.setViewName("postsList");
		}

		System.out.println("modeland view:.........................." + "         " + modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/createPost", method = RequestMethod.GET)
	public ModelAndView createPost() {
		ModelAndView modelAndView = new ModelAndView();
		Posts posts = new Posts();
		modelAndView.addObject("posts", posts);
		modelAndView.setViewName("createPost");
		return modelAndView;
	}

	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public ModelAndView createPost(@Valid Posts posts, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		System.out.println("User we got...........:" + user.getId());
		System.out.println("post detailss ..............:" + posts);
		postService.create(posts, user);
		modelAndView.addObject("successMessage", "Post has been created successfully");
		modelAndView.addObject("posts", new Posts());
		modelAndView.setViewName("createPost");

		// }
		return modelAndView;
	}

}
