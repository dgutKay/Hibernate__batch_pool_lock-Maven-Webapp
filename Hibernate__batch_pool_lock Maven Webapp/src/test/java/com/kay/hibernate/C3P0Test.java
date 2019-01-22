package com.kay.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.kay.hibernate.util.HibernateUtil;
import java.sql.Connection;

public class C3P0Test {
	@Test
	public void checkTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		// 检验是否真的是C3P0提供的连接资源？
		// Connection connection = session.connection();
		// System.out.println(connection);

		session.getTransaction().commit();
		factory.close();
	}

}
