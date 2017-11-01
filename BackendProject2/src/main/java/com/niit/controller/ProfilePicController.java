package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePicDao;
import com.niit.modal.ProfilePic;
import com.niit.modal.User;
import com.niit.modal.Error;

@Controller
public class ProfilePicController
{
	@Autowired
	private ProfilePicDao profilePictureDao;
	
    @RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session)
	{
		String users=(String)session.getAttribute("userName");
		if(users==null)		
		{
			    Error error=new Error(3,"UnAuthorized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		ProfilePic profilePicture=new ProfilePic();
		profilePicture.setUsername(users);
		profilePicture.setImage(image.getBytes());
		profilePictureDao.saveProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePic>(profilePicture,HttpStatus.OK);
	}
		
		@RequestMapping(value="/getprofilepic/{username}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session)
		{
			System.out.println("hwllo"+username);
			
			String user=(String)session.getAttribute("userName");
			if(user==null)
				return null;
			else
			{
				ProfilePic profilePic=profilePictureDao.getProfilePic(username);
				if(profilePic==null)
					return null;
				else
					return profilePic.getImage();
			}
			
	}

}
