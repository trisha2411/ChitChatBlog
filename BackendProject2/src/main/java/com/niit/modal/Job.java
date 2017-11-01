package com.niit.modal;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="jobinfo")
public class Job 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String jobTittle;
	private String jobDescription;
	@NotEmpty
	private String skillsRequired;
	private String location;
	private String salary;
	private String companyName;
	private Date postedOn;
	private String yrsOfExp;
	
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobTittle=" + jobTittle
				+ ", jobDescription=" + jobDescription + ", skillsRequired="
				+ skillsRequired + ", location=" + location + ", salary="
				+ salary + ", companyName=" + companyName + ", postedOn="
				+ postedOn + ", yrsOfExp=" + yrsOfExp + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobTittle() {
		return jobTittle;
	}
	public void setJobTittle(String jobTittle) {
		this.jobTittle = jobTittle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public String getYrsOfExp() {
		return yrsOfExp;
	}
	public void setYrsOfExp(String yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}
	
	

}
