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

import com.niit.dao.BlogPictureDao;
import com.niit.dao.BlogPostDao;
import com.niit.modal.BlogPicture;
import com.niit.modal.BlogPost;
import com.niit.modal.Error;


@Controller
public class BlogPicController 
{
	/*@Autowired
	private BlogPictureDao blogPictureDao;
	
	@Autowired
	private BlogPostDao blogPostDao;*/
	
    /*@RequestMapping(value="/uploadblogpic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadBlogPicture(@RequestParam CommonsMultipartFile image,HttpSession session)
	{
		String users=(String)session.getAttribute("userName");
		
		System.out.println("inside uploadblogpicture");
		BlogPost blogPost=new BlogPost();
		int blogid= blogPost.getId();
		System.out.println("blog id is"+blogid);
		if(users==null)		
		{
			    Error error=new Error(3,"UnAuthorized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		BlogPicture blogPicture=new BlogPicture();
		blogPicture.setBlogid(blogid);
		blogPicture.setImage(image.getBytes());
		blogPictureDao.saveBlogPicture(blogPicture);
		return new ResponseEntity<BlogPicture>(blogPicture,HttpStatus.OK);
	}
		
		@RequestMapping(value="/getblogpic/{blogid}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable int blogid,HttpSession session)
		{
			System.out.println("inside getblogpic"+blogid);
			
			String user=(String)session.getAttribute("userName");
			
			if(user==null)
				return null;
			else
			{
				BlogPicture blogPic=blogPictureDao.getBlogPic(blogid);
				if(blogPic==null)
					return null;
				else
					return blogPic.getImage();
			}
			
	}*/

}
