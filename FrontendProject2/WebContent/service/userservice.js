/**
 * UserService
 */

app.factory('UserService',function($http)
		{
	        var userService={}
	        var BASE_URL="http://localhost:8080/BackendProject2"
	        	/*
	        	 * user=$scope.user from controller  => $scope.user is ng-modal=user.x
	        	 */
	        	
	        	userService.registerUser=function(user)
	        	{    
	        	    console.log("registeruser working")
	                
	                console.log(user)
	                return $http.post(BASE_URL + "/registration",user)
	            }
	            
	            userService.login=function(user)
	            {
	            	return $http.post(BASE_URL + "/login",user)
	            	
	            }
	            userService.logout=function()
	            {
	            	return $http.put(BASE_URL + "/logout")
	            	
	            }
	            userService.getuser=function()
	            {
	            	return $http.get(BASE_URL + "/getuser")
	            	
	            }
	            userService.updateUser=function(user)
	            {
	            	return $http.put(BASE_URL + "/updateprofile",user)
	            	
	            }
	            userService.enterUserForForgotPassword=function(user)
	            {
	            	console.log("entering user for forgot password")
	            	return $http.post(BASE_URL + "/checkuserforforgotpassword",user)
	            	
	            }
	            userService.newPassword=function(user)
	            {
	            	return $http.put(BASE_URL + "/enternewpassword",user)
	            	
	            }
	            
	            return userService;
	        
	        
		})
