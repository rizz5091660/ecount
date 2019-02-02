package com.sanisoft.ecount.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaBalanceDao;
import com.sanisoft.ecount.entity.CoaBalance;

@Repository("CoaBalanceDao")
public class CoaBalanceDaoImpl extends EcountDao implements CoaBalanceDao{

	@Override
	public int create(CoaBalance coaBalance) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coaBalance);
		return coaBalance.getId();
	}

	@Override
	public CoaBalance get(int id) {
		Session session = sessionFactory.getCurrentSession();		
		return session.get(CoaBalance.class, id);
	}

	@Override
	public int update(CoaBalance coaBalance) {
		Session session = sessionFactory.getCurrentSession();
		session.update(coaBalance);
		return EcountConstant.SUCCESS;
	}

	@Override
	public CoaBalance getByCoaIdMontYear(int coaId, int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CoaBalance cb where cb.coa.id=:coaId and cb.month=:month and cb.year=:year");
		query.setInteger("coaId", coaId);
		query.setInteger("month",month);
		query.setInteger("year",year);
		return (CoaBalance) query.uniqueResult();
	}

}
