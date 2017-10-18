package com.shera.shikkhok.sherashikkhok.service;

import java.util.List;

import com.shera.shikkhok.sherashikkhok.model.Posts;
import com.shera.shikkhok.sherashikkhok.model.User;

public interface PostsService {
	
	List<Posts> getAllPosts();
	
    List<Posts> findLatest5();
    
    void insert (Posts post);
    
    List<Posts> findByCreatorId(long userId);
    
	void create(Posts post, User user);
        
    Posts edit(Posts post);
    
    boolean deletePost(Long postId);

}
