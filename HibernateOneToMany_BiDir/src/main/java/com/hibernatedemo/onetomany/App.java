package com.hibernatedemo.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA One-To-Many Unidirectional
 *
 */
public class App {
	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "App";

	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// This Method Is Used To Retrieve The 'EntityManager' Object
	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public static void main(String[] args) {
		try {
			createBranch();
			//retrieveBranch(22);
			//retrieveStudent(36);
			//deleteBranch(22);
			/*
			 * Student s1 = new Student(); Student s2 = new Student(); if (s1 == s2) {
			 * System.out.println("S1 = S2"); }
			 */

		} finally {
			emFactoryObj.close();
		}
	}

	public static void createBranch() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		try {
			Branch branch = new Branch();
			branch.setBranchShortName("CSE");
			branch.setBranchName("Computer Science and Engineering");
			branch.setDescription(
					"CSE department offers courses under ambitious curricula in computer science and computer engineering..");
			List<Student> students = new ArrayList<Student>();

			Student student1 = new Student();
			student1.setFname("Peter");
			student1.setLname("Milanovich");
			student1.setContactNo("+1-408-575-1317");
			students.add(student1);

			Student student2 = new Student();
			student2.setFname("Rosy");
			student2.setLname("Larsen");
			student2.setContactNo("+1-408-575-1219");
			students.add(student2);
			
			Student student3 = new Student();
			student3.setFname("Peter");
			student3.setLname("Milanovich");
			student3.setContactNo("+1-408-575-1317");
			students.add(student3);
			
			if (student1 == student2) {
				System.out.println("S1 = S2");
			}
			
			if (student1.equals(student3)) {
				System.out.println("S1 = S3");
				System.out.println(student1);
			}

			branch.setStudents(students);
			entityManager.persist(branch);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public static void retrieveBranch(int branchId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		try {
			Branch branch = entityManager.find(Branch.class, branchId);
			System.out.println("Branch Short Name: " + branch.getBranchShortName());

			for (Student std : branch.getStudents()) {
				System.out.println("Student ID: " + std.getId() + ", Name: " + std.getFname() + " " + std.getLname());
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public static void retrieveStudent(int studentId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		try {
			Student student = entityManager.find(Student.class, studentId);
			System.out.println("Student Name: " + student.getFname() + " " + student.getLname());
			Branch branch = student.getBranch();
			System.out.println("Branch Short Name: " + branch.getBranchShortName());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public static void deleteBranch(int branchId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		try {
			Branch branch = entityManager.find(Branch.class, branchId);
			System.out.println("Branch Short Name: " + branch.getBranchShortName());
			entityManager.remove(branch);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}
}