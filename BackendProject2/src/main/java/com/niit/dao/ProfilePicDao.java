package com.niit.dao;

import com.niit.modal.ProfilePic;

public interface ProfilePicDao 
{

	ProfilePic getProfilePic(String username);
	void saveProfilePicture(ProfilePic profilePicture);
}
