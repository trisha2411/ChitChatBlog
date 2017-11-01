package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.modal.Job;
import com.niit.modal.User;
@Repository
@Transactional
public class JobDaoImpl implements JobDao
{
     @Autowired
     private SessionFactory sessionFactory;
     
	public void saveJob(Job job) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		
	}
	
	public List<Job> getAllJobs() 
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		List<Job> jobs=query.list();
		int size=query.list().size();
		System.out.println("NUMBER OF OBJECT IN JOB LIST"+size);
		return jobs;
		
		
	}

	public Job getJobById(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class,id);
		return job;
	}

	public Job getJobDetails(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class,id);
		return job;
		
		
	}
}
