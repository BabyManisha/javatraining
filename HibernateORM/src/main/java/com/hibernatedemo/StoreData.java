package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;

public class StoreData {
	public static void main(String[] args) {

		StoreData application = new StoreData();

		Employee emp1 = application.saveEmployee("Sam", "Disilva", "Maths");
		Employee emp2 = application.saveEmployee("Joshua", "Brill", "Science");
		Employee emp3 = application.saveEmployee("Peter", "Pan", "Physics");
		Employee emp4 = application.saveEmployee("Bill", "Laurent", "Maths");

		// get All Employee Data::::::::;

		List<Employee> employees = application.getAllEmployees();
		System.out.println("List of all persisted employee >>>");
		for (Employee employee : employees) {
			System.out.println("Persisted employee :" + employee);
		}

		// update method:::::::::::::
		application.updateEmployee(emp4.getId(), "scienc123");

		// delete method.............
		application.deleteEmployee(emp4.getId());

	}

	public Employee saveEmployee(String firstName, String lastName, String section) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setSection(section);

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		session.close();
		return employee;
	}

	// get All Employees Data

	public List<Employee> getAllEmployees() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Employee> employees = (List<Employee>) session.createQuery("FROM Employee e ORDER BY e.firstName ASC")
				.list();

		session.getTransaction().commit();
		session.close();
		return employees;
	}

	/**
	 * This method updates a specific Student object
	 */
	public void updateEmployee(int id, String section) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Employee employee = (Employee) session.get(Employee.class, id);
		employee.setSection(section);
		// session.update(student);//No need to update manually as it will be updated
		// automatically on transaction close.
		session.getTransaction().commit();
		session.close();
	}

	public void deleteEmployee(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Employee employee = (Employee) session.get(Employee.class, id);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
	}
}