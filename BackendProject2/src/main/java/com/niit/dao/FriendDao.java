package com.niit.dao;

import java.util.List;

import com.niit.modal.Friend;
import com.niit.modal.User;

public interface FriendDao
{
	List<User> getAllSuggestedUsers(String username);//A - (BUC)

	void friendRequest(Friend friend);//insert into friend

	List<Friend> pendingRequests(String username);

	void updatePendingRequest(Friend friend);
	List<Friend> listOfFriends(String username);
	public List<String> getMutualFriends(String username, String otherUsername);


}
