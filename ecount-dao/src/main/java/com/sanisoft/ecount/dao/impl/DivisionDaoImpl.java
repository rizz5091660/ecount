package com.sanisoft.ecount.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.DivisionDao;

@Repository("DivisionDao")
public class DivisionDaoImpl extends EcountDao implements DivisionDao{

	@Override
	public List<Object[]> getAllForDropDown() {
		Session session =sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Object[]> query = session.createQuery("select d.id, d.name from Division d");
		List<Object[]> list = query.list();
		return list;
	}

}
