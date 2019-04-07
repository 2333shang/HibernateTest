package com.hx.service;


import java.util.List;

import org.hibernate.Transaction;

import com.hx.dao.UserDao;
import com.hx.entities.User;
import com.hx.statictools.HibernateUtils;

public class UserService {

	private UserDao userDao = new UserDao();
	
	public User getUser(Integer id) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		User user = null;
		try {
			user = userDao.get(id);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return user;
	}
	
	public User loadUser(Integer id) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		User user = null;
		try {
			user = userDao.load(id);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return user;
	}
	
	public List<User> getUsers() {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<User> users = null;
		try {
			users = userDao.gets();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return users;
	}
	
	public void deleteUser(User user) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		try {
			userDao.delete(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public void updateUser(User user) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		try {
			userDao.update(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public void saveUser(User user) {
		Transaction transaction = null;
		try {
			transaction = HibernateUtils.getCurrentSession().beginTransaction();
			userDao.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public void saveOrUpdateUser(User user) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		try {
			userDao.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public void mergeUser(User user) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		try {
			userDao.merge(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
}
