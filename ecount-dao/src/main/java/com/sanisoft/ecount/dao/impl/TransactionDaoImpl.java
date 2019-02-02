package com.sanisoft.ecount.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.TransactionDao;
import com.sanisoft.ecount.entity.Transaction;
import com.sanisoft.ecount.entity.TransactionDetail;

@Repository
public class TransactionDaoImpl extends EcountDao implements TransactionDao {

	@Override
	public int create(Transaction trxn) {
		Session session = sessionFactory.getCurrentSession();
		session.save(trxn);
		return trxn.getId();
	}

	@Override
	public Transaction get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Transaction.class, id);
	}

	@Override
	public int update(Transaction trxn) {
		Session session = sessionFactory.getCurrentSession();
		session.update(trxn);
		return EcountConstant.SUCCESS;
	}

	@Override
	public int createDetail(TransactionDetail trxnDtl) {
		Session session = sessionFactory.getCurrentSession();
		session.save(trxnDtl);
		return trxnDtl.getId(); 
	}

}
