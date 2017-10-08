package com.shera.shikkhok.sherashikkhok.service;

import com.shera.shikkhok.sherashikkhok.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);
	
	public void saveUser(User user);

}
