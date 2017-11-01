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

import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.modal.Job;
import com.niit.modal.Error;
import com.niit.modal.User;

@Controller
public class JobController 
{
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job, HttpSession session)
	{
		if(session.getAttribute("userName")==null)
		{   // user is not authenticated[login is not executed]
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		}
		// how to check role?
		String username=(String)session.getAttribute("userName");
		System.out.println(username);
		User user=userDao.getUserByUsername(username); // it will return the complete row of the table
		System.out.println(user.getRole());
		if(user.getRole().equals("ADMIN"))
		{    System.out.println("hwyy"+user.getRole());
			try
			{
				System.out.println("heyy"+new Date());
				job.setPostedOn(new Date());
				System.out.println("heyy1"+job);
			    jobDao.saveJob(job);
			    return new ResponseEntity<Job>(job,HttpStatus.OK);
			}
			catch(Exception e)
			{
				Error error=new Error(7,"Unable to insert job details.."+e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//2nd callback function
			}
		}
		else
		{
			Error error=new Error(6,"Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//2nd callback function
		}
	}	
	
	
		@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
		public ResponseEntity<?> getAllJobs(HttpSession session)
		{
			/*System.out.println(session.getAttribute("userName"));
			if(session.getAttribute("userName")==null)
			{
				Error error=new Error(5,"Unauthorized access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}*/
			
			List<Job> jobs=jobDao.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> getjobbyid(@PathVariable int id,HttpSession session)
		{
			if(session.getAttribute("userName")==null)
			{
				Error error=new Error(5,"Unauthorized access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			
			Job job=jobDao.getJobById(id);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		
		@RequestMapping(value="/getjobdetails/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> getjobdetails(@PathVariable int id,HttpSession session)
		{
			System.out.println("getjobdetails"+session.getAttribute("userName"));
			if(session.getAttribute("userName")==null)
			{
				Error error=new Error(5,"Unauthorized access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			
			Job job=jobDao.getJobDetails(id);
			System.out.println("job coming from Dao"+job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
			
		}
	

}
