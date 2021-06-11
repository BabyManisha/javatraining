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
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			entityManager = emFactoryObj.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			Branch branch = new Branch();
			branch.setBranchShortName("CSE");
			branch.setBranchName("Computer Science and Engineering");
			branch.setDescription(
					"CSE department offers courses under ambitious curricula in computer science and computer engineering..");
			List<Student> students = new ArrayList<Student>();
			students.add(getStudent1());
			students.add(getStudent2());
			branch.setStudents(students);
			// Branch object having all the information (Branch and Students)
			entityManager.persist(branch);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
			emFactoryObj.close();
		}
	}

	private static Student getStudent1() {
		Student student = new Student();
		student.setFname("Peter");
		student.setLname("Milanovich");
		student.setContactNo("+1-408-575-1317");

		return student;
	}

	private static Student getStudent2() {
		Student student = new Student();
		student.setFname("Rosy");
		student.setLname("Larsen");
		student.setContactNo("+1-408-575-1219");

		return student;
	}
}