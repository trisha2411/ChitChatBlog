/**
 * Job Service
 */

app.factory('JobService',function($http)
{
	var jobService={}
	 var BASE_URL="http://localhost:8080/BackendProject2"
		 
		 jobService.saveJob=function(job)
		 {
		     console.log("job are saving")
            
             console.log(job)
             return $http.post(BASE_URL + "/savejob",job)
		 }
	
	   /* jobService.getJobById=function(id)
	    {
	    	console.log("getting job id")
	    	alert("Getting Jobs By their Id")
	    	return $http.get(BASE_URL +"/getjobbyid"+id)
	    }*/
	    jobService.listOfJobs=function()
	    {
	    	console.log("getting list of jobs")
	    	
	    	return $http.get(BASE_URL +"/getalljobs")
	    }
	    jobService.getJobDetailsById=function(id)
	    {
	    	console.log("getting job details by id")
	    	
	    	console.log(id)
	    	return $http.get(BASE_URL +"/getjobdetails/"+id)
	    }
	
	return jobService;
	
})