package com.hx.dao;

import java.util.List;

import com.hx.entities.User;
import com.hx.statictools.HibernateUtils;

public class UserDao {

	public User get(Integer id) {
		return (User) HibernateUtils.getCurrentSession().get(User.class, id);
	}
	
	public User load(Integer id) {
		return (User) HibernateUtils.getCurrentSession().load(User.class, id);
	}
	
	public List<User> gets() {
		String hql = "from User";
		return HibernateUtils.getCurrentSession().createQuery(hql).list();
	}
	
	public void save(User user) {
		HibernateUtils.getCurrentSession().save(user);
	}
	
	public void update(User user) {
		HibernateUtils.getCurrentSession().update(user);
	}
	
	public void saveOrUpdate(User user) {
		HibernateUtils.getCurrentSession().saveOrUpdate(user);
	}
	
	public void merge(User user) {
		HibernateUtils.getCurrentSession().merge(user);
	}
	
	public void delete(User user) {
		HibernateUtils.getCurrentSession().delete(user);
	}
}
