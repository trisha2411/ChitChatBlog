package com.niit.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.BlogPictureDao;
import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.modal.BlogComment;
import com.niit.modal.BlogPicture;
import com.niit.modal.BlogPost;
import com.niit.modal.Error;
import com.niit.modal.Job;
import com.niit.modal.ProfilePic;
import com.niit.modal.User;

@Controller
public class BlogPostController 
{
	@Autowired
	private BlogPostDao blogPostDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogPictureDao blogPictureDao;
	
	@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost, HttpSession session)
	{   
		System.out.println("hello"+session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{   // user is not authenticated[login is not executed]
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		}
		
		String username=(String)session.getAttribute("userName");
		System.out.println(username);
			try
			{
				
				blogPost.setPostedOn(new Date());
				System.out.println("1 line");
				User postedBy=userDao.getUserByUsername(username);
				System.out.println("2 line");
				blogPost.setPostedBy(postedBy);//value for foreign key
				System.out.println("3 line");
				
				 blogPostDao.saveBlogPost(blogPost);
				 
				
			   
			    session.setAttribute("blogid", blogPost.getId());
			    System.out.println("BLOG ID IS "+blogPost.getId());
			    
			    
				return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Error error=new Error(7,"Unable to insert job details.."+e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//2nd callback function
			}
				
		}
		
	@RequestMapping(value="/getallblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogPost(@PathVariable int approved,HttpSession session)
	{
		/*System.out.println("hello"+session.getAttribute("userName"));
		System.out.println(approved);
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		
		}*/
		List<BlogPost> blogPosts=blogPostDao.getBlogPost(approved);
		System.out.println(blogPosts);
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblogpost/{id}")
	public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session)
	{
		System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost=blogPostDao.getAllBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
	{

	    System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try
		{
		if(!blogPost.isApproved()&& blogPost.getRejectionReason()==null)
			blogPost.setRejectionReason("Not Mentioned");//blogpost is reject but reason not mentioned
		  blogPostDao.updateBlogPost(blogPost);//update the value for approval property 0/1
		 return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(6,"Unable to update blogpost");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	
	}
	
	@RequestMapping(value="/blogpostapprovalstatus",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostApprovalStatus(HttpSession session)
	{
		 System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String)session.getAttribute("userName");
		List<BlogPost> blogPosts=blogPostDao.getApprovalStatus(username);
		System.out.println(blogPosts);
		return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session)
	{
		System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		
		String username=(String)session.getAttribute("userName");
		User user=userDao.getUserByUsername(username);
		System.out.println("inside add blog comment"+user);
		blogComment.setCommentedBy(user);//set the value for foreign key 'username' in blogcomment table
		blogComment.setCommentedOn(new Date());//set the value for commentedOn
		try
		{
		blogPostDao.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(7,"Unable to add blogcomment " + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
   @RequestMapping(value="/getblogcomments/{blogPostId}")
	public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session)
	{
	   System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		
		List<BlogComment> blogComments=blogPostDao.getAllBlogComments(blogPostId);
		System.out.println("inside get blog comments"+blogComments);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}

   
   
   /*@RequestMapping(value="/addBlogPicture",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogPicture(@RequestParam("image") CommonsMultipartFile image,HttpSession session)
	{
	        System.out.println("print image object" + " "+image);
			System.out.println("In addblogpic" + " "+session.getAttribute("blogid"));
		
		//String username=(String)session.getAttribute("username");
		Integer picname= (Integer) session.getAttribute("blogid");
		System.out.println("In addblogpic2" + picname);
			byte[] arr;
			if(!image.isEmpty())
			{
				
			try
			{
			arr=image.getBytes();
			System.out.println("first line");
			String path="E:\\"+picname+".jpg";
			System.out.println("2 line");
			File f=new File(path);
			System.out.println("3 line");
			FileOutputStream ff=new FileOutputStream(("D:\\demo.txt"));
			ff.write(0);
			//BufferedOutputStream bf=new BufferedOutputStream(new FileOutputStream(f));
			System.out.println("4 line");
			ff.close();
			System.out.println("5 line");
			System.out.println("Image Uploaded");
		
		}
		catch(Exception e)
		{System.out.println("catch");
		  
		}
			System.out.println("inside if block");
			}
			return new ResponseEntity<Void>(HttpStatus.OK);	
		}
		*/
	
	/*@RequestMapping(value="/getblogimage/{blogid}",method=RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable int blogid,HttpSession session)
	{
		    System.out.println("Inside getblogimage");
			BlogPicture blogPicture=blogPostDao.getBlogPic(blogid);
			if(blogPicture==null)
				return null;
			System.out.println(blogPicture.getImage());
			return blogPicture.getImage();
		
	}*/

   @RequestMapping(value="/uploadblogpic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadBlogPicture(@RequestParam CommonsMultipartFile image,HttpSession session)
	{
		String users=(String)session.getAttribute("userName");
		
		System.out.println("inside uploadblogpicture");
		BlogPost blogPost=new BlogPost();
		int blogid=(Integer)session.getAttribute("blogid");
		System.out.println(blogid);
		System.out.println("blog id is"+blogid);
		if(users==null)		
		{
			    Error error=new Error(3,"UnAuthorized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		BlogPicture blogPicture=new BlogPicture();
		blogPicture.setBlogid(blogid);
		blogPicture.setImage(image.getBytes());
		blogPostDao.saveBlogPicture(blogPicture);
		return new ResponseEntity<BlogPicture>(blogPicture,HttpStatus.OK);
	}
		
		@RequestMapping(value="/getblogpic/{blogid}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable int blogid,HttpSession session)
		{
			System.out.println("inside getblogpic"+blogid);
			
			String user=(String)session.getAttribute("userName");
			/*int blogid1=(Integer)session.getAttribute("blogid");
			System.out.println(blogid1);*/
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
		}
	



}
