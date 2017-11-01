package com.niit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.modal.Error;
import com.niit.modal.Friend;
import com.niit.modal.User;
import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;

@Controller
public class FriendController 
{
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> suggestedUsersList(HttpSession session)
	{
		String username=(String)session.getAttribute("userName");
		System.out.println("inside suggestusers"+username);
		if(username==null)
		{
			Error error=new Error(5,"UnAuthorized Access..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<User> suggestedUsers=friendDao.getAllSuggestedUsers(username);
		System.out.println("printing suggestedusers list"+suggestedUsers);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}
	
	
	    @RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)
		public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session)
		{
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println("inside friend request"+username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	
	    	Friend friend=new Friend();
	    	friend.setFromId(username);
	    	friend.setToId(toId);
	    	friend.setStatus('P');
	    	friendDao.friendRequest(friend);
	    	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
	    
	    
	    @RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	    public ResponseEntity<?> pendingRequests(HttpSession session)
	    {
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println("inside pending requests controller"+username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	
	    	List<Friend> pendingRequests=friendDao.pendingRequests(username);
	    	System.out.println("inside pending requests controller"+pendingRequests);
	    	return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	    	
	    }
	    
	    
	    @RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	    public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend,HttpSession session)
	    {
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println("inside update pending requests controller"+username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	
	    	friendDao.updatePendingRequest(friend);//updated status (A/D)
	    	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	    }
	    
	    
	    @RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)
	    public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session)
	    {
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println("inside get users details controller"+username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	
	    	User user= userDao.getUserByUsername(fromId);
	    	System.out.println("inside get users details controller"+user);
	    	return new ResponseEntity<User>(user,HttpStatus.OK);
	    }
	    
	    
	    @RequestMapping(value="/getfriends",method=RequestMethod.GET)
	    public ResponseEntity<?> getFriendsList(HttpSession session)
	    {
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println("inside get friends controller"+username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	List<Friend> friends=friendDao.listOfFriends(username);
	    	System.out.println("inside get friends controller"+friends);
	    	return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/getmutualfriends",method=RequestMethod.PUT)
	    //input is List<User> -> suggestedUsers
	    public ResponseEntity<?> getMutualFriends(@RequestBody List<User> suggestedUsers,HttpSession session)
	    {
	    	String username=(String)session.getAttribute("userName");
	    	System.out.println(username);
	    	if(username==null)
	    	{
	    		Error error=new Error(5,"UnAuthorized Access..");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
	    	
	    	Map<String, List<String>> mutualFriends=new HashMap<String, List<String>>();
	    	for(User u :suggestedUsers)
	    	{
	    	mutualFriends.put(u.getUserName(), friendDao.getMutualFriends(username,u.getUserName()));
	    	System.out.println(mutualFriends.size());
	    	}
	    	System.out.println(mutualFriends);
	    	return new ResponseEntity<Map<String,List<String>>>(mutualFriends,HttpStatus.OK);
	    }

}
