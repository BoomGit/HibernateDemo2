package com.boom.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	static Configuration cfg=null;
	static SessionFactory sessionFactory=null;
	//静态代码段实现（在代码预加载时创建而且只创建一次）
	//注意static中只运行static中的代码
	static{
	cfg=new Configuration();
	cfg.configure();
	sessionFactory=cfg.buildSessionFactory();
	}
	//提供返回与本地线程绑定的session方法
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();
	}
	
	//提供返回sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void main(String[] args){
		
	}
	
}
