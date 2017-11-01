package com.niit.dao;

import com.niit.modal.BlogPicture;
import com.niit.modal.ProfilePic;


public interface BlogPictureDao 
{

	BlogPicture getBlogPic(int blogid);
	void saveBlogPicture(BlogPicture blogPicture);
}
