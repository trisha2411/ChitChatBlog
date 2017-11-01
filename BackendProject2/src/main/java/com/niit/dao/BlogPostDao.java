package com.niit.dao;

import java.util.List;

import com.niit.modal.BlogComment;
import com.niit.modal.BlogPicture;
import com.niit.modal.BlogPost;

public interface BlogPostDao 
{
	void saveBlogPost(BlogPost blogPost);
    List<BlogPost> getBlogPost(int approved);
    BlogPost getAllBlogPost(int id);
    void updateBlogPost(BlogPost blogPost);
    List<BlogPost> getApprovalStatus(String username);
    void addBlogComment(BlogComment blogComment);

    List<BlogComment> getAllBlogComments(int blogPostId);
   
    BlogPost getBlogPic(int id);
    void saveBlogPicture(BlogPicture blogPicture);
}
