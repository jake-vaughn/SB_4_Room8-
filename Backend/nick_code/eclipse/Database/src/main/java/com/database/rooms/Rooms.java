package com.database.rooms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="rooms")
public class Rooms 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id; 
	
	@Column(name="title")
	private String title;
	
	public Rooms(String roomName)
	{
		this.title = roomName; 
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setRoomName(String roomName)
	{
		this.title = roomName; 
	}
	
	public String getId()
	{
		return this.id; 
	}
	
	public String getTitle()
	{
		return this.title; 
	}
	
	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Rooms))
			return false; 
		Rooms room = (Rooms) o;
		return this.id == room.id && this.title == room.title;
	}

}
