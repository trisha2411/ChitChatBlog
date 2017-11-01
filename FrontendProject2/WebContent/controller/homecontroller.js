/**
 * HomeController 
 */
app.controller('HomeController',function($scope,BlogPostService,JobService,$rootScope,$location)
{
	function getApprovalStatus()
	{
		BlogPostService.getApprovalStatus().then(function(response)
		{
			/*
			 * list of blog posts which are approved /rejected by admin and user is not yet notified
			 */
			console.log(response.data)
			$rootScope.approvalStatus=response.data
			console.log($rootScope.approvalStatus.length)
		},function(response)
		{
			console.log(response.status)
		})
	}
	
	getApprovalStatus();
	
	$scope.listOfJobs=function()
	{
		JobService.listOfJobs().then(function(response){
			
			console.log(response.data)
			console.log(response.status)
			$scope.jobs=response.data
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/login')
		})
		
		
	}
	
	$scope.listOfBlogApproved=function()
	{
		BlogPostService.listOfBlogApproved().then(function(response){
			
			console.log(response.data)
			console.log(response.status)
			$scope.blogPostApproved=response.data
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	$scope.listOfBlogApproved();
	
   
	$scope.listOfJobs();
	
	
})