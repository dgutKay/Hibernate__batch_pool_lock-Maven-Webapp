package com.kay.hibernate;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

import com.kay.hibernate.entity.Teacher;
import com.kay.hibernate.entity.User;
import com.kay.hibernate.util.HibernateUtil;

public class LockTest {
	/**
	 * 悲观锁 通过数据库提供的锁机制来实现的 方式就是
	 */
	@Test
	public void pessimisticLockTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Teacher where id=1");
		LockOptions lockOption = LockOptions.UPGRADE;
		query.setLockOptions(lockOption);
		Teacher teacher = (Teacher) query.uniqueResult();

		session.getTransaction().commit();
		factory.close();
	}

	/*
	 * 乐观锁 CAS ABA version
	 */
	@Test
	public void optimisticLockTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User user = session.get(User.class, 1);
		user.setUsername("aa");

		session.getTransaction().commit();
		factory.close();
	}
}
