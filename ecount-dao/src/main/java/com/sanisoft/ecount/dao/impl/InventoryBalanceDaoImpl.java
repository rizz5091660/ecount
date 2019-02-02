package com.sanisoft.ecount.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.InventoryBalanceDao;
import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.InventoryBalance;

@Repository("InventaryBalanceDao")
public class InventoryBalanceDaoImpl extends EcountDao implements InventoryBalanceDao{

	@Override
	public int create(InventoryBalance invBalance) {
		Session session = sessionFactory.getCurrentSession();
		session.save(invBalance);
		return invBalance.id;
	}

	@Override
	public int update(Inventory invBalance) {
		return EcountConstant.SUCCESS;
	}

	@Override
	public int delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		InventoryBalance inv = session.get(InventoryBalance.class, id);
		session.delete(inv);
		return EcountConstant.SUCCESS;
	}

	@Override
	public InventoryBalance get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(InventoryBalance.class, id);
	}

}
