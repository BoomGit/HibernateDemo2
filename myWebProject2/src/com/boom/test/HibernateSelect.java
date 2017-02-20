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

	// ����Ĺ淶����
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
			int i=10/0; //�������
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
			//�뱾���̰߳󶨵�session
			session = HibernateUtils.getSessionObject();
			//��������
			tx = session.beginTransaction();
			//���
			User user=new User();
			user.setUsername("kkk");
			user.setPassword("845");
			session.save(user);
			//int i=10/0; //�������
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			//sessionFactory.close();
		}
	}
	
	/*
	 * �ؼ��㣺testTx()��testTx1()����
	 * 1.open��session��Ҫ�ֶ�session�ر�
	 * 2.ͨ�����̵߳�session����Ҫ�ֶ��ر�session�����߳̽���֮��sessionҲ���Զ�����
	 */
	
	
	// ��֤һ���������
	@Test
	public void testCasch() {
		// ���ù������ȡsessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		// ��ȡsession
		Session session = sessionFactory.openSession();
		// ��������
		Transaction tx = session.beginTransaction();

		// 1.����uid��ѯ
		// ִ�е�һ��get�����Ƿ��ѯ���ݿ⣬�Ƿ���sql���
		User user = session.get(User.class, 6);
		System.out.println(user);
		// 2.����uid��ѯ
		// ִ�еڶ���get�����Ƿ��ѯ���ݿ⣬�Ƿ���sql���
		User user1 = session.get(User.class, 6);
		System.out.println(user1);
		// �ύ����
		tx.commit();
		// �ر���Դ
		session.close();
		sessionFactory.close();
	}

}
