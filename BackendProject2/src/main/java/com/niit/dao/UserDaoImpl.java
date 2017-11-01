package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.modal.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
    @Autowired
    private SessionFactory sessionFactory;
    
	public void registration(User user) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
	}

	public boolean isEmailValid(String email) 
	{
		System.out.println(email);
		Session session=sessionFactory.getCurrentSession();
		Query qe=session.createQuery("from User where email=:email");
		qe.setParameter("email", email);
		User user=(User)qe.uniqueResult();
		if(user==null)
		{
			return true;   // email doesn't exist
		}
		else
		   return false;    // email already exist
	}

	public boolean isNameValid(String userName) 
	{
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, userName);
		if(user==null)
		{
			return true;  // userName doesn't exist
		}
		else
		   return false;  // userName already exist
	}

	public User login(User user) 
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where userName=? and password=?");
		query.setString(0, user.getUserName());
		query.setString(1, user.getPassword());
		User user1=(User)query.uniqueResult();
		return user1;
	}
	
	public User updatePassword(User user) 
	{
		Session session=sessionFactory.getCurrentSession();
		String userName=user.getUserName();
		String password=user.getPassword();
		Query query=session.createQuery("update User set password=:password where userName=:userName");
		query.setString("userName", userName);
		query.setString("password", password);
		query.executeUpdate();
		return user;
	}

	public void updateUser(User validUser)
	{
		
		System.out.println("Inside user dao "+validUser);
		Session session=sessionFactory.getCurrentSession();
		session.update(validUser);// update set online=true where userName=?
		
	}

	public User getUserByUsername(String username) 
	{
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);// select * from user where userName=?
		return user;
	}

	public User checkforuser(User user) 
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where userName=? and question=? and answer=?");
		query.setString(0, user.getUserName());
		query.setString(1, user.getQuestion());
		query.setString(2, user.getAnswer());
		User user1=(User)query.uniqueResult();
		return user1;
	}
	

}
