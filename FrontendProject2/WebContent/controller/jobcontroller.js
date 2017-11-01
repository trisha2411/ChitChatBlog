/**
 * Job Controller
 */

app.controller("JobController",function($scope,$location,JobService,$routeParams)
{
	var id=$routeParams.id
	
	$scope.saveJob=function()
	{
		JobService.saveJob($scope.job).then(function(response)
		{
			
			console.log(response.data)
			alert("Added job successfully....")
	        $location.path('/home')
			
		},function(response)
		{
			console.log(response.status)
      	  console.log(response.data)
      	  $scope.error=response.data
      	     
	        	 if(response.status==401)
	        	  $location.path('/login')
	        	 $location.path('/savejob')
		})
	}
	
	/*$scope.getJobById=function(id)
	{
		console.log("Job id is"+id)
		JobService.getJobById(id).then(function(response){
			
			console.log(response.data)
			$scope.job=response.data
			console.log($scope.job)
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	*/
	
//	
//     $scope.getJobDetails=function()
//	{     console.log($scope.id)
		/*JobService.getJobDetails(id).then(function(response){
			 console.log("h"+id)
			console.log(response.data)
			$scope.jobs=response.data
			console.log(response.status)
			
			console.log("jobs")
			//$location.path('/getjobdetails')
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
		})
//	}
//     $scope.getJobDetails();*/
	/*
	 * 
	 */
	
	
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
   
	$scope.listOfJobs();
})
