package com.sanisoft.ecount.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.BranchDao;

@Repository("BranchDao")
public class BranchDaoImpl extends EcountDao implements BranchDao {

	@Override
	public List<Object[]> getAllForDropDown() {
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select br.id, br.name from Branch br");
		List<Object[]> list = query.list();
		return list;
	}

}
