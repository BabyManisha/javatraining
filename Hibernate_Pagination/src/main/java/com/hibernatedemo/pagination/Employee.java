package com.hibernatedemo.pagination;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@Column(name = "E_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	private String firstName;
	private String lastName;
	private int salary;

	public Employee() {
	}

	public Employee(int eId, String firstName, String lastName, int salary) {
		super();
		this.eId = eId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}