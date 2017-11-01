package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.modal.ProfilePic;

@Repository
@Transactional
public class ProfilePicDaoImpl implements ProfilePicDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveProfilePicture(ProfilePic profilePicture) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);

	}

	public ProfilePic getProfilePic(String username) 
	{
		Session session=sessionFactory.getCurrentSession();
		//select * from profilepicture where username='admin'
		ProfilePic profilePicture=(ProfilePic)session.get(ProfilePic.class, username);
		return profilePicture;
	}

}
