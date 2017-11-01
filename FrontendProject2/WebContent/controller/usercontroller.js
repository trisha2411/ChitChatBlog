/**
 * UserController
 */

app.controller('UserController',function($scope,$location,$rootScope,$cookieStore,UserService)
		{
	
	
	/*
	 * Registration [data from registrationform.html to UserController
	 * inserting the data into user table
	 */
	
	       $scope.registerUser=function()
	       {
	    	   console.log("usercontroller in angular is working")
	    	   console.log($scope.user)
	    	   UserService.registerUser($scope.user).then(function(response)
	    			   {
	    		          console.log(response.status)
	    		          console.log("hello "+$scope.user)
	    		          $scope.success="Registered seccessfully..please login again"
	    		        	  $location.path('/home')
	    		        },function(response)
                           {
	    		        	  console.log(response.status)
	    		        	  console.log(response.data)
	    		        	  $scope.error=response.data
	    		        	     if($scope.error.code==2)
	    		        	    	  $scope.duplicateEmail=response.data
	    		        	     if($scope.error.code==3)
		    		        	      $scope.duplicateUername=response.data
		    		        	 if($scope.error.code==1)
		    		        	      $scope.exception=response.data
		    		        	 $location.path('/registration')
	    		        	  
                           })
                           
	       }
	    			  
	       /*
	        * login
	        * data is from login.html to UserController
	        * validating the user credentials
	        *
	        */
	       
	       
	        $scope.login=function()
	        {   console.log($scope.user)
	        	UserService.login($scope.user).then(function(response)
	        			{  
	        		       console.log(response.data)
	        		     $rootScope.currentUser=response.data
	        		     console.log("hello1"+$rootScope.currentUser.userName)
	        		     console.log("hello user"+$rootScope.currentUser.role)
	        		       $cookieStore.put('currentUser',response.data)
	        		       console.log("hello"+$rootScope.currentUser.userName)
	        		       $location.path('/home')
	        			},function(response){
	        				console.log(response.data)
	        				$scope.loginFail="Invalid username/password"
	        			    $location.path('/login')
	        			
	        			})
	        }
	        
	       /* UserService.getUser().then(function(response)
	        {
	        	
	           $scope.user=response.data	
	        },function(response){
	        	if(response.status==401)
	        		$location.path('/login')
	        })*/
		   
	        $scope.updateUser=function()
	        {
	        	console.log($scope.user)
	        	UserService.updateUser($scope.user).then(function(response){
	        		alert('Updates the details successfully')
	        		$location.path('/home')
	        	},function(response){
	        		if(response.status==401)
	        			$location.path('/login')
	        			$scope.error=response.data
	        			$location.path('/editprofile')
	        	})
	        }
	        
	        $scope.enterUserForForgotPassword=function()
	        {
	        	console.log($scope.user)
	        	UserService.enterUserForForgotPassword($scope.user).then(function(response){
	        		alert(' the details are succesfully entered')
	        		console.log(response.data)
	        		console.log(response.status)
	        		$scope.userForgot=response.data
	        		console.log($scope.userForgot)
	        		$location.path('/enternewpassword')
	        	},function(response){
	        		console.log(response.data)
	        		console.log(response.status)
	        		if(response.status==401)
	        			$location.path('/forgotpassword')
	        			
	        	})
	        }
	        
	        $scope.newPassword=function()
	        {
	        	console.log($scope.user)
	        	UserService.newPassword($scope.user).then(function(response){
	        		alert('Entering new password')
	        		$location.path('/home')
	        	},function(response){
	        		if(response.status==401)
	        			$location.path('/forgotpassword')
	        			
	        	})
	        }
		})