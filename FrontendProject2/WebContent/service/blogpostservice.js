/**
 * BlogPostService
 */
app.factory('BlogPostService',function($http){
	
	var blogPostService={}
	 var BASE_URL="http://localhost:8080/BackendProject2"
		 
		 blogPostService.saveBlogPost=function(blogPost)
		 {
		     console.log("Blogs are saving")
            
            return $http.post(BASE_URL + "/saveblogpost",blogPost)
		 }
	 blogPostService.listOfBlogWaitingForApproval=function()
	 {
	     console.log("Blogs are saving")
        alert('Blogs are waiting for approved')
        return $http.get(BASE_URL + "/getallblogs/"+0)
	 }
	 blogPostService.listOfBlogApproved=function()
	 {
	     console.log("Blogs are approved")
       
        return $http.get(BASE_URL + "/getallblogs/"+1)
	 }
	 blogPostService.getBlogPostById=function(id)
	 {
		    console.log("Getting Blog post By id")
			return $http.get(BASE_URL + "/getblogpost/"+id)
	 }
	 blogPostService.updateApproval=function(blogPost)
	 {
			console.log(blogPost)
			return $http.put(BASE_URL + "/updateblogpost",blogPost)
		}
	 blogPostService.getApprovalStatus=function()
	 {
			return $http.get(BASE_URL + "/blogpostapprovalstatus")
		}
	 
	 blogPostService.updateBlogPost=function(blogPost)
	 {
		    console.log(blogPost)
			return $http.put("http://localhost:8080/BackendProject2/updateblogpost",blogPost)
	 }
	 blogPostService.addComment=function(blogComment)
	 {
		    console.log(blogComment)
			return $http.post("http://localhost:8080/BackendProject2/addblogcomment",blogComment)
	 }
	 blogPostService.getBlogComments=function(blogPostId)
	 {
		 console.log(blogPostId)
			return $http.put("http://localhost:8080/BackendProject2/getblogcomments/"+blogPostId)
		}
	return blogPostService;
})