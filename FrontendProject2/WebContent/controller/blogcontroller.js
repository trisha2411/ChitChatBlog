/**
 * Blog Controller
 */

app.controller('BlogPostController',function($scope,$location,BlogPostService){
	
	$scope.saveBlogPost=function()
	{
		BlogPostService.saveBlogPost($scope.blogPost).then(function(response){
			
			console.log(response.data)
			console.log(response.status)
			sub();
			alert("Blogs are added successfuly and waiting for approval.")
			$location.path('/home')
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
				else
				$location.path('/saveblogpost')
			
		})
	}
	
	$scope.listOfBlogWaitingForApproval=function()
	{
		BlogPostService.listOfBlogWaitingForApproval().then(function(response){
			
			console.log(response.data)
			console.log(response.status)
			$scope.blogPostWaitingForApproval=response.data
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
	$scope.listOfBlogWaitingForApproval();
	
	function sub()
	{
		console.log("inside sub function()")
		var x=document.getElementById('myFile').files[0];
		var data=new FormData();
		data.append('image',x);
		$.ajax({
			url:'http://localhost:8080/BackendProject2/uploadblogpic',
			type:'POST',
			data:data,
			enctype:'multipart/form-data',
			cache:false,
			processData:false,
			contentType:false,
			success:function(res)
			{
			//	alert(res.status);
			},
			error:function(res)
			{
			//		alert(res.status);
			}
		});	
	}
})