/**
 * BlogPostDetailcontroller
 */
app.controller('BlogPostDetailController',function($scope,$location,BlogPostService,$routeParams)
{
	var id=$routeParams.id
	$scope.showRejectionTxt=false
	$scope.showcomments=false
	$scope.isLiked=false
	console.log(id)
    $scope.blogPost=BlogPostService.getBlogPostById(id).then(function(response)
	{
		console.log(response.data)
		console.log("id of blog"+id)
		$scope.blogPost=response.data
		console.log($scope.blogPost)
		
	},function(response)
	{
		console.log(response.data)
		if(response.status==401)
			$location.path('/login')
		
	})
    
	
	$scope.updateApproval=function()
	{
		console.log($scope.blogPost)
		BlogPostService.updateApproval($scope.blogPost).then(function(response)
		{
			$location.path('/getallblogs')
		},function(response)
		{
			if(response.data==401)
				$location.path('/login')
			else
			$location.path('/blogpostdetails/'+id)
		})
	}
    
	$scope.setValueForRejectionTxt=function(val)
	{
		$scope.showRejectionTxt=val
	}
	
	/*
	 * select * from blogcomment where blogpost_id=246   [list of blogcomments]
	 */
	function getBlogComments()
	{
		BlogPostService.getBlogComments(id).then(function(response)
		{
			console.log("inside getblogcomments()")
			console.log(response.data)
			$scope.blogComments=response.data
			console.log($scope.blogComments)
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	
	
	/*$scope.getComments=function()
	{
		$scope.showcomments=true;
	}*/
	getBlogComments()
	$scope.updateBlogPost=function()
	{
		console.log($scope.blogPost)
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response)
		{
			console.log("inside updateblogpost()")
			console.log(response.data)
			console.log(response.status)
			$location.path('/getallblogs')
		},function(response)
		{   
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.addComment=function()
	{
		/*
		 * Assignment statement which will assign blogPost object to blogPost property in blogcomment object
		 * $scope.blogPost is an JSON object of type BlogPost
		 * $scope.blogComment is an JSON object of type BlogComment
		 * blogComment has {id , blogPost, commentedBy, commentedOn, commentText }
		 * $scope.blogComment.blogPost=$scope.blogPost
		 */
		$scope.blogComment.blogPost=$scope.blogPost
		/*
		 * blogComment : {blogPost:{id:'',blogTitle:'',postedBy:'',postedOn:''},commentText:'Good'}
		 */
		console.log($scope.blogComment)
		BlogPostService.addComment($scope.blogComment).then(function(response)
		{
			$scope.blogComment.commentText=''
				getBlogComments()
			console.log(response.data)
			console.log(response.status)
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			$location.path('/getblogpostbyid/'+id)
		})
	}
	
	
	$scope.updateLikes=function()
	{
		$scope.isLiked=!$scope.isLiked
		if($scope.isLiked)
			{
			  $scope.blogPost.likes=$scope.blogPost.likes+1
			}
		else
			{
			$scope.blogPost.likes=$scope.blogPost.likes-1
			}
		
		BlogPostService.updateApproval($scope.blogPost).then(function(response){
			console.log(response.data)
		},function(response){
			console.log(response.data)
		})
	}
	
	
	
	
})