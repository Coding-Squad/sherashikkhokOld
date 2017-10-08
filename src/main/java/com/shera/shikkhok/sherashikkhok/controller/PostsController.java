package com.shera.shikkhok.sherashikkhok.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shera.shikkhok.sherashikkhok.model.Posts;
import com.shera.shikkhok.sherashikkhok.service.PostsService;

@Controller
public class PostsController {

	@Autowired
	private PostsService postService;
	
	@RequestMapping(value="/createPost", method = RequestMethod.GET)
	public ModelAndView createPost(){
		ModelAndView modelAndView = new ModelAndView();
		Posts posts = new Posts();
		modelAndView.addObject("posts", posts);
		modelAndView.setViewName("createPost");
		return modelAndView;
	}
	
	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public ModelAndView createPost(@Valid Posts posts, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		/*Posts postsExists = postService.findById(posts.getId());
		if (postsExists != null) {
			bindingResult
					.rejectValue("id", "error.posts",
							"This post already is in archive");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("createPost");
		} else {*/
			System.out.println("post detailss ..............:"+posts);
			postService.create(posts);
			modelAndView.addObject("successMessage", "Post has been created successfully");
			modelAndView.addObject("posts", new Posts());
			modelAndView.setViewName("createPost");
			
		//}
		return modelAndView;
	}
	
}
