/**
 * FriendService
 */
app.factory('FriendService',function($http)
{
	var friendService={}
	var BASE_URL="http://localhost:8080/BackendProject2"
		
		
	friendService.suggestedUsers=function()
	{
		console.log("inside suggesteduser service")
		return $http.get(BASE_URL + "/suggestedusers")//List of user objects
	}
	friendService.sendFriendRequest=function(toId)
	{
		console.log("inside sendfriendrequest service")
		return $http.post(BASE_URL +"/friendrequest/"+toId)
	}
	
	friendService.pendingRequests=function()
	{
		console.log("inside pendingrequest service")
		return $http.get(BASE_URL + "/pendingrequests")
	}
	friendService.updatePendingRequest=function(request)
	{
		console.log("inside updatependingrequest service")
		return $http.put(BASE_URL + "/updatependingrequest",request)//request is Friend object with updated status(A/D).
	}
	friendService.getUserDetails=function(fromId)
	{
		console.log("inside getuserdetails service")
		return $http.get(BASE_URL + "/getuserdetails/"+fromId)
	}
	
	friendService.getFriends=function()
	{
		console.log("inside getfriends service")
		return $http.get(BASE_URL + "/getfriends")
	}
	friendService.getMutualFriends=function(suggestedUsers)
	{
		return $http.put(BASE_URL + "/getmutualfriends",suggestedUsers)
	}
	
	return friendService;
})