package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.UserDao;
import com.niit.modal.Error;
import com.niit.modal.User;


@Controller
public class UserController 
{
	
	public UserController()
	{
		System.out.println("CREATING INSTANCE FOR USERCONTROLLER");
	}
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	//T -> ?  -Any Type of Entity
	public ResponseEntity<?>  registration(@RequestBody User user)
	{
		if(!userDao.isEmailValid(user.getEmail()))
		{
			Error error=new Error(2,"Duplicate email address..");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			
		}
		if(!userDao.isNameValid(user.getUserName()))
		{
			Error error=new Error(3,"Username alreadu exist... please enter different username");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			
		}
		try
		{
			userDao.registration(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(1,"Unable to register"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
	{
		
		User validUser=userDao.login(user);
		System.out.println(validUser);
		if(validUser==null)
		{
			Error error=new Error(4,"Invalid username/password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
	    validUser.setIsonline("F");
		userDao.updateUser(validUser);
		session.setAttribute("userName", validUser.getUserName());
		
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
		
	}

	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session)
	{
		String username=(String)session.getAttribute("userName");
		System.out.print(username);
		if(username==null)
		{
			Error error=new Error(5,"Please Login..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		User user=userDao.getUserByUsername(username);
		user.setIsonline("T");
		userDao.updateUser(user);  // update online status to false
		session.removeAttribute("userName");
		session.invalidate();
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	//T -> ?  -Any Type of Entity
	public ResponseEntity<?> getUser(HttpSession session)
	{
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		    String username=(String)session.getAttribute("userName");
		    User user=userDao.getUserByUsername(username);  // select * from user where userName=?
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	//T -> ?  -Any Type of Entity
	public ResponseEntity<?> updateUser( @RequestBody User user,HttpSession session)
	{
		System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		 System.out.println("hello");
		 String username=(String)session.getAttribute("userName");
		   
		   try{
			   System.out.println("hello"+session.getAttribute("userName"));
			   System.out.println(user);
			   user.setUserName(username);
			  
			   userDao.updateUser(user);
			   return new ResponseEntity<User>(user,HttpStatus.OK);
				
		  }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   System.out.println(e);
			   Error error=new Error(6,"Unable to update user details.."+e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
				
		   }
	}
	
	
	@RequestMapping(value="/checkuserforforgotpassword",method=RequestMethod.POST)
	public ResponseEntity<?> checkUser(@RequestBody User user,HttpSession session)
	{
		System.out.println("checkuserforforgotpassword controller backend");
		User checkUser=userDao.checkforuser(user);
		System.out.println(checkUser);
		if(checkUser==null)
		{
			Error error=new Error(4,"Invalid username/password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
	    
		session.setAttribute("userName", checkUser.getUserName());
		
		return new ResponseEntity<User>(checkUser,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/enternewpassword",method=RequestMethod.PUT)
	//T -> ?  -Any Type of Entity
	public ResponseEntity<?> newPassword( @RequestBody User user,HttpSession session)
	{
		System.out.println("entering enter new password controller backend");
		System.out.println(session.getAttribute("userName"));
		if(session.getAttribute("userName")==null)
		{
			Error error=new Error(5,"Unauthorized access..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		 System.out.println("hello");
		 String username=(String)session.getAttribute("userName");
		 System.out.println("hiii"+username);
		   try{
			   System.out.println("hello"+session.getAttribute("userName"));
			   System.out.println(user);
			   user.setUserName(username);
			  
			   userDao.updatePassword(user);
			   System.out.println(user);
			   return new ResponseEntity<User>(user,HttpStatus.OK);
				
		  }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   System.out.println(e);
			   Error error=new Error(6,"Unable to update user details.."+e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
				
		   }
	}
	

}
