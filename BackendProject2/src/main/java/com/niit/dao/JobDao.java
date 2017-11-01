package com.niit.dao;

import java.util.List;

import com.niit.modal.Job;

public interface JobDao 
{ 
	void saveJob(Job job);
    List<Job> getAllJobs();
    Job getJobById(int id);
    Job getJobDetails(int id);
}
