package com.shera.shikkhok.sherashikkhok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.shera.shikkhok.sherashikkhok.model.Posts;
import com.shera.shikkhok.sherashikkhok.model.User;
import com.shera.shikkhok.sherashikkhok.repository.PostsRepository;
import com.shera.shikkhok.sherashikkhok.service.PostsService;

@Service("postsService")
@Primary
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsRepository postRepository;

	@Override
	public List<Posts> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public List<Posts> findLatest5() {
		return null;
	}

	@Override
	public void insert(Posts post) {
		postRepository.save(post);
	}

	@Override
	public Posts edit(Posts post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePost(Long id) {

		Posts thePost = postRepository.findOne(id);
		if (thePost == null)
			return false;
		postRepository.delete(id);
		return true;

	}

	@Override
	public void create(Posts post, User user) {

		post.setUserId(user.getId());
		post.setEmail(user.getEmail());
		postRepository.save(post);

	}

	@Override
	public List<Posts> findByCreatorId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
