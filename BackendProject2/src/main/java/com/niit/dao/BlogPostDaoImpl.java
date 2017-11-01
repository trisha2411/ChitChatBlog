package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.modal.BlogComment;
import com.niit.modal.BlogPicture;
import com.niit.modal.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
		public void saveBlogPost(BlogPost blogPost) 
		{
			Session session=sessionFactory.getCurrentSession();
			System.out.println("show"+blogPost);
			session.saveOrUpdate(blogPost);

		}

		public List<BlogPost> getBlogPost(int approved) 
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=null;
			if(approved==1)
			{
				query=session.createQuery("from BlogPost where approved="+approved);
				
			}
			else
				query=session.createQuery("from BlogPost where approved=0 and rejectionreason=null");
			
			return query.list();
		}
		
		
		public BlogPost getAllBlogPost(int id) 
		{
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
			return blogPost;
		}
		public void updateBlogPost(BlogPost blogPost)
		{
			Session session=sessionFactory.getCurrentSession();
			session.update(blogPost);//update blogpost set approved=?.... where id=?
			
		}
		
		public List<BlogPost> getApprovalStatus(String username) 
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogPost where postedBy.userName=? and viewedStatus=? and (approved=1 or rejectionreason!=null)");
			query.setString(0, username);
			query.setBoolean(1, false);
			List<BlogPost> blogPosts=query.list();
			for(BlogPost bp:blogPosts){
				bp.setViewedStatus(true);
				
				session.update(bp);
			}
			return blogPosts;
		}

		public void addBlogComment(BlogComment blogComment) 
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(blogComment);//insert into blogcomment 
			
		}

		public List<BlogComment> getAllBlogComments(int blogPostId) 
		{
			Session session=sessionFactory.getCurrentSession();
			
			//select * from blogcomment_batch5 where blogpost_id=246
			Query query=session.createQuery("from BlogComment where blogPost.id=?");
			query.setInteger(0, blogPostId);
			return query.list();
		}
		
		
		
		public BlogPost getBlogPic(int blogid)
		{
			Session session=sessionFactory.getCurrentSession();
			//select * from profilepicture where username='admin'
			BlogPost blogPicture=(BlogPost)session.get(BlogPost.class, blogid);
			return blogPicture;
		}

		public void saveBlogPicture(BlogPicture blogPicture) 
		{
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(blogPicture);
			
		}
}
