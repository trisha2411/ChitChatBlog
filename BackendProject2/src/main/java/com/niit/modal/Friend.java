package com.niit.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friendinfo")
public class Friend 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String fromId;
	private String toId;
	private char status;
	@Override
	public String toString() {
		return "Friend [id=" + id + ", fromId=" + fromId + ", toId=" + toId
				+ ", status=" + status + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	

}
