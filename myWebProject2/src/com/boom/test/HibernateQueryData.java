package com.boom.test;


import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.boom.user.User;
import com.boom.utils.HibernateUtils;

public class HibernateQueryData {

	//ʹ��query����ʹ�ð��̵߳�session��
	@Test
	public void testQuery(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//��ȡsessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//���߳�session
			session=HibernateUtils.getSessionObject();
			//��������
			tx=session.beginTransaction();
			//��ѯ����
			//1.����Query����
				Query query =session.createQuery("from User");
			
			//2.����query���������ķ����õ����
			List<User> list = query.list();
			for(User user : list){
				System.out.println(user);
			}
			//�ύ����
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	
	//Criteria����
	@Test
	public void testCriteria(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//��ȡsessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//���߳�session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//��������
			tx=session.beginTransaction();
			//��ѯ����
			//1.����Criteria����
			Criteria criteria =session.createCriteria(User.class);
			//2.���÷����õ����
			List<User> list=criteria.list();
			for(User user : list){
				System.out.println(user);
			}
			//3.��������
			//�ύ����
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	//SQLQuery����
	@Test
	public void testSQLQuery1(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//��ȡsessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//���߳�session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//��������
			tx=session.beginTransaction();
			//��ѯ����
			//1.����SQLQuery����
			SQLQuery sqlquery=session.createSQLQuery("select * from t_user");
			//����һ  ���������
			//2.���÷����õ����
			List<Object[]> list=sqlquery.list();
			
			//3.��������
			for(Object[] objects : list){
				System.out.println(Arrays.toString(objects));
			}
			/*//������  ���ÿ�������Ƕ���
			//��ӵ���ʵ����ȥ
			sqlquery.addEntity(User.class);
			List<User> list1=sqlquery.list();
			for(User user : list1){
				System.out.println(user);
			}*/
			
			//�ύ����
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	//SQLQuery����
	@Test
	public void testSQLQuery2(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//��ȡsessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//���߳�session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//��������
			tx=session.beginTransaction();
			//��ѯ����
			//1.����SQLQuery����
			SQLQuery sqlquery=session.createSQLQuery("select * from t_user");
		/*	//����һ  ���������
			//2.���÷����õ����
			List<Object[]> list=sqlquery.list();
			
			//3.��������
			for(Object[] objects : list){
				System.out.println(Arrays.toString(objects));
			}*/
			//������  ���ÿ�������Ƕ���
			//��ӵ���ʵ����ȥ
			sqlquery.addEntity(User.class);
			List<User> list1=sqlquery.list();
			for(User user : list1){
				System.out.println(user);
			}
			
			//�ύ����
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	
	
}
