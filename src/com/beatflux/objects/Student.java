package com.beatflux.objects;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Student {		
	private int id;
	private String firstName;
	private String lastName;
	private String personalNumber;
	private Timestamp registertimestamp;

    @XmlAttribute
	public String getPN() {
		return personalNumber;
	}
	public void setPN(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlAttribute
	public String getFirstName() {
		return firstName;
	}
	public void setName(String firstName) {
		this.firstName = firstName;
	}
	@XmlAttribute
	public String getLastName() {
		return lastName;
	}
	public void setPass(String lastName) {
		this.lastName = lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlAttribute
	public Timestamp getRegistertimestamp() {
		return registertimestamp;
	}
	public void setRegistertimestamp(Timestamp registertimestamp) {
		this.registertimestamp = registertimestamp;
	}
}


