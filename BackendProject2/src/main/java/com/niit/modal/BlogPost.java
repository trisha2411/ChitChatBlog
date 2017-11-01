package com.niit.modal;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="blogpostinfo")
public class BlogPost 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String blogTitle;
	@Lob
	private String blogDescription;
	
	private Date postedOn;
	@ManyToOne
	private User postedBy;
	private boolean approved;
	private String rejectionReason;
	private boolean viewedStatus;
	private int likes;
	

	
	
	
	
	
	
	

	


	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", blogTitle=" + blogTitle
				+ ", blogDescription=" + blogDescription + ", postedOn="
				+ postedOn + ", postedBy=" + postedBy + ", approved="
				+ approved + ", rejectionReason=" + rejectionReason
				+ ", viewedStatus=" + viewedStatus + ", likes=" + likes + "]";
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public boolean isViewedStatus() {
		return viewedStatus;
	}
	public void setViewedStatus(boolean viewedStatus) {
		this.viewedStatus = viewedStatus;
	}
	
	

}
