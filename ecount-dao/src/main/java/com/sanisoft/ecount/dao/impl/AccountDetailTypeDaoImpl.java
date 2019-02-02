package com.sanisoft.ecount.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.AccountDetailTypeDao;


@Repository("AccountDetailTypeDao")
public class AccountDetailTypeDaoImpl extends EcountDao implements AccountDetailTypeDao{

	@Override
	public List<Object[]> getAllForDropDown() {
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select at.id, at.name, at.accountType.id from AccountDetailType at");
		List<Object[]> list = query.list();
		return list;
	}

}
