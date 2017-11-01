package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.modal.BlogPicture;
import com.niit.modal.ProfilePic;

@Repository
@Transactional
public class BlogPictureDaoImpl implements BlogPictureDao 
{
	@Autowired
	private SessionFactory sessionFactory;

	public BlogPicture getBlogPic(int blogid)
	{
		Session session=sessionFactory.getCurrentSession();
		//select * from profilepicture where username='admin'
		BlogPicture blogPicture=(BlogPicture)session.get(BlogPicture.class, blogid);
		return blogPicture;
	}

	public void saveBlogPicture(BlogPicture blogPicture)
	{
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(blogPicture);

	}

}
