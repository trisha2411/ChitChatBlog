/**
 * FriendController
 */
app.controller('FriendController',function($scope,$rootScope,FriendService,$location)
{
	$scope.showUserDetails=false;
	 $scope.show=false
	function getSuggestedUsers()
	{
		FriendService.suggestedUsers().then(function(response)
		{
			
			console.log("inside suggestedusers()"+response.data)
			$scope.suggestedUsers=response.data
			 getMutualFriends($scope.suggestedUsers)//add this line 
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
			else
			console.log(response.status)
		})
	}
	
	
	function pendingRequests()
	{
		FriendService.pendingRequests().then(function(response)
		{
			console.log("inside pendingRequests()"+response.data)
			$scope.pendingRequests=response.data//List of Friend objects [use only fromId])
			console.log($scope.pendingRequests)
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.sendFriendRequest=function(toId)
	{
		FriendService.sendFriendRequest(toId).then(function(response)
		{
			getSuggestedUsers();
			$location.path('/suggestedusers')
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
			else
			console.log(response.status)
		})
	}
	
	
	$scope.updatePendingRequest=function(request,value)
	{
		console.log('pending request ' + request)
		request.status=value //value is 'A' for accept and 'D' for delete
		console.log('after assigning value to status  ' + request)
		FriendService.updatePendingRequest(request).then(function(response)
		{
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response)
	    {
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	$scope.getUserDetails=function(fromId)
	{
		$scope.showUserDetails=true
		FriendService.getUserDetails(fromId).then(function(response)
		{
			console.log("inside showusersDetails()"+response.data)
			$scope.user=response.data
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function getMutualFriends(suggestedUsers)
	{
		alert('getmutualfriends')
		FriendService.getMutualFriends(suggestedUsers).then(function(response)
		{
			alert("in success" + response.data)
			$scope.mutualFriends=response.data
			console.log($scope.mutualFriends)
		},function(response){
			alert(response.status)
			console.log(response.status)
			$location.path('/home')
		})
	}
	
	
	function getFriends()
	{
	FriendService.getFriends().then(function(response)
	{
		console.log("inside getfriends()"+response.data)
		$scope.friends=response.data //List<Friend> select * from friend where status='A' and (fromId=? or toId=?)
		$rootScope.noOfFriends=$scope.friends.length
	},function(response)
	{ 
		console.log(response.status)
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	 $scope.showFriends=function()
	 {
	        $scope.show=true;
	    }
	
	getSuggestedUsers();
	pendingRequests()
	getFriends();
})