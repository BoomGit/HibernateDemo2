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

	//使用query对象（使用绑定线程的session）
	@Test
	public void testQuery(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//获取sessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//绑定线程session
			session=HibernateUtils.getSessionObject();
			//开启事务
			tx=session.beginTransaction();
			//查询操作
			//1.创建Query对象
				Query query =session.createQuery("from User");
			
			//2.调用query对象的里面的方法得到结果
			List<User> list = query.list();
			for(User user : list){
				System.out.println(user);
			}
			//提交事务
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	
	//Criteria对象
	@Test
	public void testCriteria(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//获取sessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//绑定线程session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//开启事务
			tx=session.beginTransaction();
			//查询操作
			//1.创建Criteria对象
			Criteria criteria =session.createCriteria(User.class);
			//2.调用方法得到结果
			List<User> list=criteria.list();
			for(User user : list){
				System.out.println(user);
			}
			//3.遍历数据
			//提交事务
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	//SQLQuery对象
	@Test
	public void testSQLQuery1(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//获取sessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//绑定线程session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//开启事务
			tx=session.beginTransaction();
			//查询操作
			//1.创建SQLQuery对象
			SQLQuery sqlquery=session.createSQLQuery("select * from t_user");
			//方案一  获得是数组
			//2.调用方法得到结果
			List<Object[]> list=sqlquery.list();
			
			//3.遍历数据
			for(Object[] objects : list){
				System.out.println(Arrays.toString(objects));
			}
			/*//方案二  获得每个部分是对象
			//添加到是实体中去
			sqlquery.addEntity(User.class);
			List<User> list1=sqlquery.list();
			for(User user : list1){
				System.out.println(user);
			}*/
			
			//提交事务
			tx.commit();
		}catch(Exception exception){
			exception.printStackTrace();
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory.close();	
		}
	}
	//SQLQuery对象
	@Test
	public void testSQLQuery2(){
		SessionFactory sessionFactory=null;
		Session  session=null;
		Transaction tx=null;
		try{
			//获取sessionfactory
			sessionFactory=HibernateUtils.getSessionFactory();
			//绑定线程session(HibernateUtils.getSessionObject();)
			//session=HibernateUtils.getSessionObject();
			session=sessionFactory.openSession();
			//开启事务
			tx=session.beginTransaction();
			//查询操作
			//1.创建SQLQuery对象
			SQLQuery sqlquery=session.createSQLQuery("select * from t_user");
		/*	//方案一  获得是数组
			//2.调用方法得到结果
			List<Object[]> list=sqlquery.list();
			
			//3.遍历数据
			for(Object[] objects : list){
				System.out.println(Arrays.toString(objects));
			}*/
			//方案二  获得每个部分是对象
			//添加到是实体中去
			sqlquery.addEntity(User.class);
			List<User> list1=sqlquery.list();
			for(User user : list1){
				System.out.println(user);
			}
			
			//提交事务
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
