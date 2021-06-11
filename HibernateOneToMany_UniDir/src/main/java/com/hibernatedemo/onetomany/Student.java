package com.hibernatedemo.onetomany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private int id;

	/*
	 * When you use a @GenericGenerator that references the native strategy,
	 * Hibernate uses the strategy natively supported by the configured Dialect. You
	 * can find the corresponding code in the of the Dialect class. If you use a
	 * MySQL database dialect, you can see that Hibernate utilizes an auto-increment
	 * for primary key generation.
	 */

	@Column(name = "CONTACT_NO")
	private String contactNo;

	private String fname;// if Field name and Column name is same no need to map with annotation

	private String lname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
}