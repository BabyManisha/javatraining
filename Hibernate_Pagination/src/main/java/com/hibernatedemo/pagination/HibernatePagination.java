package com.hibernatedemo.pagination;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernatePagination {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			Employee employee;
			for (int i = 0; i < 100; i++) {
				employee = new Employee();
				employee.setFirstName("employe_" + i);
				employee.setLastName("surname_" + i);
				employee.setSalary(10000 * i);
				session.save(employee);
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		session = sessionFactory.getCurrentSession();

		int pageNumber = 5;
		int pageSize = 10;

		try {
			session.beginTransaction();

			Query query = session.createQuery("from Employee");
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			List<Employee> employees = (List<Employee>) query.list();
			if (employees != null) {
				System.out.println("Total Results:" + employees.size());
				for (Employee employee : employees) {
					System.out.println(employee.geteId() + " - " + employee.getFirstName());
				}
			}
			/*
			 * Criteria criteria = session.createCriteria(Employee.class);
			 * criteria.setFirstResult(0); criteria.setMaxResults(pageSize); List<Employee>
			 * firstPage = criteria.list();
			 * 
			 * if (employees != null) { System.out.println("Total Results:" +
			 * employees.size()); for (Employee employee : employees) {
			 * System.out.println(employee.geteId() + " - " + employee.getFirstName()); } }
			 */
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

}
