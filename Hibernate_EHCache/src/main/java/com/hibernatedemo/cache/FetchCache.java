package com.hibernatedemo.cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FetchCache {
	public static void main(String[] args) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();

		Session session1 = factory.openSession();
		Employee emp1 = (Employee) session1.load(Employee.class, 20);
		System.out.println(emp1.geteId() + " " + emp1.getFirstName() + " " + emp1.getLastName());
		session1.close();

		Session session2 = factory.openSession();
		Employee emp2 = (Employee) session2.load(Employee.class, 20);
		System.out.println(emp2.geteId() + " " + emp2.getFirstName() + " " + emp2.getLastName());
		session2.close();
	}
}