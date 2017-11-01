package com.niit.dao;

import com.niit.modal.User;

public interface UserDao 
{
	void registration(User user);
	boolean isEmailValid(String email);
	boolean isNameValid(String userName);
	User login(User user);
	User updatePassword(User user);
	void updateUser(User validUser);
	User getUserByUsername(String username);
	User checkforuser(User user);

}
