package com.boom.test;

import java.nio.channels.SeekableByteChannel;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.boom.user.User;
import com.boom.utils.HibernateUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

public class HibernateDemo {

	@Test
	public void testAdd() {
		// ��һ�� ����hibernate���������ļ�
		// ��src�����ҵ�������hibernate��cfg.xml
		// ��hibernate�����װ����
		//Configuration cfg = new Configuration();
		//cfg.configure();
		// �ڶ��� ����SessionFactory����
		// ��ȡhibernate���������ļ����ݣ�����sessionFactory
		// �ڹ����У�����ӳ���ϵ�����������ݿ�����ѱ���
		//SessionFactory sessionFactory = cfg.buildSessionFactory();
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		// ������ ʹ��SeesionFactory����session����
		// ������
		Session session = sessionFactory.openSession();
		// ���Ĳ� ��������
		Transaction tx = session.beginTransaction();
		// ���岽 д�����߼�CRUD����
		// ��ӵĹ���
		User user = new User();
		user.setUsername("У��");
		user.setPassword("1234456");
		session.save(user);
		// ������ �ύ����
		tx.commit();
		// ���߲� �ر���Դ
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testGet(){
	//���ù������ȡsessionFactory
	SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
	//��ȡsession
	Session session = sessionFactory.openSession();
	//��������
	Transaction tx=session.beginTransaction();
	//����id��ѯ
	//����session��get����
	//��һ��������ʵ�����class
	//�ڶ��������� id��ֵ
	User user=session.get(User.class, 1);
	System.out.println(user);
	
	//�ύ����
	tx.commit();
	//�ر���Դ
	session.close();
	sessionFactory.close();
	}
	
	@Test
	public void  testUpdate(){
		//���ù������ȡsessionFactory
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		//��ȡsession
		Session session = sessionFactory.openSession();
		//��������
		Transaction tx=session.beginTransaction();
		
		//�޸Ĳ���
		//����id��ѯ
		User user =session.get(User.class, 1);
		//�򷵻ص�user�������������޸�֮���ֵ
		user.setUsername("444");
		//����session�ķ���update�޸�
		//ִ�й��̣���user���������ҵ�uidֵ������uid���и���
		session.update(user);
		System.out.println(user);
		//�ύ����
		tx.commit();
		//�ر���Դ
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testDelete(){
		//���ù������ȡsessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//��ȡsession
				Session session = sessionFactory.openSession();
				//��������
				Transaction tx=session.beginTransaction();
				//ɾ������
				//��һ�ָ���idɾ������
//				User user =session.get(User.class, 3);
//				session.delete(user);
				//�ڶ���
				User user =new User();
				user.setUid(4);
				session.delete(user);
				//�ύ����
				tx.commit();
				//�ر���Դ
				session.close();
				sessionFactory.close();
	}
	
	@Test
	public void testSaveOrUpdate1(){
		//���ù������ȡsessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//��ȡsession
				Session session = sessionFactory.openSession();
				//��������
				Transaction tx=session.beginTransaction();
				//��Ӳ���
				User user=new User();
				user.setUid(1);  //����id����������id�費���ö�û��
				user.setUsername("250");
				user.setPassword("654123");
				session.save(user);
				//�ύ����
				tx.commit();
				//�ر���Դ
				session.close();
				sessionFactory.close();
		
	}
	//����id���޸Ĳ�ͬ
	@Test
	public void testSaveOrUpdate2(){
		//���ù������ȡsessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//��ȡsession
				Session session = sessionFactory.openSession();
				//��������
				Transaction tx=session.beginTransaction();
				//��uid=1�ļ�¼username�޸ģ�����ֵ�ǿյ�  ��Ϊ����ȫ�����ã�
				User user =new User();
				user.setUid(5);
				user.setUsername("888");
				session.update(user);
				//�ύ����
				tx.commit();
				//�ر���Դ
				session.close();
				sessionFactory.close();
		
	}
	
	@Test
	public void testSaveOrUpdate(){
		//���ù������ȡsessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//��ȡsession
				Session session = sessionFactory.openSession();
				//��������
				Transaction tx=session.beginTransaction();
				//1.��Ӳ�����˲ʱ̬��
				User user=new User();
				user.setUsername("king");
				user.setPassword("521");
				
				//2.���Ĳ������й�̬��
				User user2=new User();
				user2.setUid(6);
				user2.setUsername("boom");
				user2.setPassword("888");
				//3.���Ĳ������־�̬��
				User user3=session.get(User.class, 6);
				user3.setUsername("miss");
				user3.setPassword("999");
				session.saveOrUpdate(user);
				//�ύ����
				tx.commit();
				//�ر���Դ
				session.close();
				sessionFactory.close();
		
	}
}
