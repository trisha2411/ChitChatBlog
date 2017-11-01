/**
 *JobDetailsController 
 */
app.controller('JobDetailsController',function($scope,$location,JobService,$routeParams)
{
	var id=$routeParams.id
	
    JobService.getJobDetailsById(id).then(function(response)
	{
		console.log(response.data)
		console.log("id of job"+id)
		$scope.jobDetails=response.data
		console.log($scope.jobDetails)
		
	},function(response)
	{
		console.log(response.data)
		if(response.status==401)
			$location.path('/login')
		
	})
    
	//}
	
	//$scope.getJobDetailsById();
	
	$scope.applyForJob=function()
	{
		alert("you successfully applied for this job")
		$location.path('/home')
	}
	
	
	
})