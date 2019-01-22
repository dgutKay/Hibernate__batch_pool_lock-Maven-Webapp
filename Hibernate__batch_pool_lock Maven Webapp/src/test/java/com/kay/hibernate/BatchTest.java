package com.kay.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import com.kay.hibernate.entity.User;
import com.kay.hibernate.util.HibernateUtil;

public class BatchTest {
	@Test
	public void batchInsertTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		long start = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			User user = new User();
			user.setUsername("u" + i);
			user.setPassword("p" + i);
			user.setBirthday(new Date());

			session.save(user);
		}

		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("cost:" + (end - start) + "ms");// 14626ms
		factory.close();
	}

	@Test
	public void batchInsertTest2() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		long start = System.currentTimeMillis();

		// 使用JDBC的方式来操作
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				// JDBC
				String sql = "insert into t_user(username,password,birthday) values(?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				int index = 0;
				for (int i = 0; i < 10000; i++) {
					preparedStatement.setString(1, "uu" + i);
					preparedStatement.setString(2, "pp" + i);
					preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));

					preparedStatement.addBatch();
					index++;
					if (index % 100 == 0) {
						preparedStatement.executeBatch();
					}
				}
				
				preparedStatement.executeBatch();
			}
		});

		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("cost:" + (end - start) + "ms");// 2370ms
		factory.close();
	}
}
