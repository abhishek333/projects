package com.asn.dao;

import com.asn.model.User;

public interface UserService {	
	public User findByEmail(String email);	
	public Long createUser(User user);	
	public void updateUser(User user);
	public void updateUserPassword(Long id, String password);
}
