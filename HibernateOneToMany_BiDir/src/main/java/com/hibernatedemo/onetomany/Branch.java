package com.hibernatedemo.onetomany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the BRANCH database table.
 * 
 */
@Entity
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "BRANCH_ID")
	private int branchId;
	/*
	 * When you use a @GenericGenerator that references the native strategy,
	 * Hibernate uses the strategy natively supported by the configured Dialect. You
	 * can find the corresponding code in the of the Dialect class. If you use a
	 * MySQL database dialect, you can see that Hibernate utilizes an auto-increment
	 * for primary key generation.
	 */
	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "BRANCH_SHORT_NAME")
	private String branchShortName;

	private String description;

	/*
	 * Cascade Delete removes all children when parent is removed. So, If you delete
	 * Branch entity, JPA deletes all its students too.
	 * 
	 * Orphan Removal removes corresponding child when you remove it from the
	 * relationships. So, if you delete one student from branch.getStudents() collection,
	 * JPA automatically removes that student from database too.
	 */
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchShortName() {
		return branchShortName;
	}

	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}