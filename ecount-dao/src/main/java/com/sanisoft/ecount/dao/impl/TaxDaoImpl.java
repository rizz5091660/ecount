package com.sanisoft.ecount.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.Tax;

@Repository
public class TaxDaoImpl extends EcountDao implements TaxDao{

	@Override
	public List<Object[]> getAllForDropDown() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Object[]> query = session.createQuery("select t.id, concat(t.name,' - ',t.percentage,'%') as label,t.percentage from Tax t");
		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public Tax get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tax tax=session.get(Tax.class, id);
		return tax;
	}

}
