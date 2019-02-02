package com.sanisoft.ecount.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.AccountTypeDao;

@Repository("AccountTypeDao")
public class AccountTypeDaoImpl extends EcountDao implements AccountTypeDao{
		
//	@Override
//	public List<AccountType> getAllForDropDown2(){
//		// TODO Auto-generated method stub
//		 Session session = sessionFactory.getCurrentSession();
//		@SuppressWarnings("unchecked")
//		List<AccountType> list= session.createCriteria(AccountType.class).list();
//		return list;
//	}
	@Override
	public List<Object[]> getAllForDropDown(){
		Session session =sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select at.id, at.name from AccountType at");
		List<Object[]> list = query.list();
		return list;
	}


}
