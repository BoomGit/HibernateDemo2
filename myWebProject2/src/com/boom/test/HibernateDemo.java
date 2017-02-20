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
		// 第一步 加载hibernate核心配置文件
		// 到src下面找到名称是hibernate。cfg.xml
		// 在hibernate里面封装对象
		//Configuration cfg = new Configuration();
		//cfg.configure();
		// 第二步 创建SessionFactory对象
		// 读取hibernate核心配置文件内容，创建sessionFactory
		// 在过程中，根据映射关系，在配置数据库里面把表创建
		//SessionFactory sessionFactory = cfg.buildSessionFactory();
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		// 第三步 使用SeesionFactory创建session对象
		// 类似于
		Session session = sessionFactory.openSession();
		// 第四步 开启事务
		Transaction tx = session.beginTransaction();
		// 第五步 写具体逻辑CRUD操作
		// 添加的功能
		User user = new User();
		user.setUsername("校长");
		user.setPassword("1234456");
		session.save(user);
		// 第六步 提交事务
		tx.commit();
		// 第七步 关闭资源
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testGet(){
	//调用工具类获取sessionFactory
	SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
	//获取session
	Session session = sessionFactory.openSession();
	//开启事务
	Transaction tx=session.beginTransaction();
	//根据id查询
	//调用session的get方法
	//第一个参数：实体类的class
	//第二个参数： id的值
	User user=session.get(User.class, 1);
	System.out.println(user);
	
	//提交事务
	tx.commit();
	//关闭资源
	session.close();
	sessionFactory.close();
	}
	
	@Test
	public void  testUpdate(){
		//调用工具类获取sessionFactory
		SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
		//获取session
		Session session = sessionFactory.openSession();
		//开启事务
		Transaction tx=session.beginTransaction();
		
		//修改操作
		//根据id查询
		User user =session.get(User.class, 1);
		//向返回的user对象里面设置修改之后的值
		user.setUsername("444");
		//调用session的方法update修改
		//执行过程：到user对象里面找到uid值，根据uid进行更改
		session.update(user);
		System.out.println(user);
		//提交事务
		tx.commit();
		//关闭资源
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testDelete(){
		//调用工具类获取sessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//获取session
				Session session = sessionFactory.openSession();
				//开启事务
				Transaction tx=session.beginTransaction();
				//删除操作
				//第一种根据id删除对象
//				User user =session.get(User.class, 3);
//				session.delete(user);
				//第二种
				User user =new User();
				user.setUid(4);
				session.delete(user);
				//提交事务
				tx.commit();
				//关闭资源
				session.close();
				sessionFactory.close();
	}
	
	@Test
	public void testSaveOrUpdate1(){
		//调用工具类获取sessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//获取session
				Session session = sessionFactory.openSession();
				//开启事务
				Transaction tx=session.beginTransaction();
				//添加操作
				User user=new User();
				user.setUid(1);  //由于id是自增长，id设不设置都没事
				user.setUsername("250");
				user.setPassword("654123");
				session.save(user);
				//提交事务
				tx.commit();
				//关闭资源
				session.close();
				sessionFactory.close();
		
	}
	//和先id再修改不同
	@Test
	public void testSaveOrUpdate2(){
		//调用工具类获取sessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//获取session
				Session session = sessionFactory.openSession();
				//开启事务
				Transaction tx=session.beginTransaction();
				//把uid=1的记录username修改（部分值是空的  因为他是全部设置）
				User user =new User();
				user.setUid(5);
				user.setUsername("888");
				session.update(user);
				//提交事务
				tx.commit();
				//关闭资源
				session.close();
				sessionFactory.close();
		
	}
	
	@Test
	public void testSaveOrUpdate(){
		//调用工具类获取sessionFactory
				SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
				//获取session
				Session session = sessionFactory.openSession();
				//开启事务
				Transaction tx=session.beginTransaction();
				//1.添加操作（瞬时态）
				User user=new User();
				user.setUsername("king");
				user.setPassword("521");
				
				//2.更改操作（托管态）
				User user2=new User();
				user2.setUid(6);
				user2.setUsername("boom");
				user2.setPassword("888");
				//3.更改操作（持久态）
				User user3=session.get(User.class, 6);
				user3.setUsername("miss");
				user3.setPassword("999");
				session.saveOrUpdate(user);
				//提交事务
				tx.commit();
				//关闭资源
				session.close();
				sessionFactory.close();
		
	}
}
