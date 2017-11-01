package com.niit.modal;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="blogpostpic")
public class BlogPicture
{
	@Id
	private int blogId;
	@Lob
	private byte[] image;
	
	
	
	
	@Override
	public String toString() {
		return "BlogPicture [blogid=" + blogId + ", image="
				+ Arrays.toString(image) + "]";
	}
	public int getBlogid() {
		return blogId;
	}
	public void setBlogid(int blogid) {
		this.blogId = blogid;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	

}
