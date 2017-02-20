package com.boom.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.boom.user.User;
import com.boom.utils.HibernateUtils;
import com.sun.xml.internal.txw2.TXW;

public class HibernateSelect {
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction tx = null;

	// 事务的规范代码
	@Test
	public void testTx() {
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			User user=new User();
			user.setUsername("kkk");
			user.setPassword("845");
			session.save(user);
			int i=10/0; //代码出错
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}


	@Test
	public void testTx1() {
		try {
			//与本地线程绑定的session
			session = HibernateUtils.getSessionObject();
			//开启事务
			tx = session.beginTransaction();
			//添加
			User user=new User();
			user.setUsername("kkk");
			user.setPassword("845");
			session.save(user);
			//int i=10/0; //代码出错
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			//sessionFactory.close();
		}
	}
	
	/*
	 * 关键点：testTx()和testTx1()方法
	 * 1.open的session是要手动session关闭
	 * 2.通过绑定线程的session不需要手动关闭session，当线程结束之后，session也会自动结束
	 */
	
	
	// 验证一级缓存存在
	@Test
	public void testCasch() {
		// 调用工具类获取sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		// 获取session
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();

		// 1.根据uid查询
		// 执行第一个get方法是否查询数据库，是否发送sql语句
		User user = session.get(User.class, 6);
		System.out.println(user);
		// 2.根据uid查询
		// 执行第二个get方法是否查询数据库，是否发送sql语句
		User user1 = session.get(User.class, 6);
		System.out.println(user1);
		// 提交事务
		tx.commit();
		// 关闭资源
		session.close();
		sessionFactory.close();
	}

}
