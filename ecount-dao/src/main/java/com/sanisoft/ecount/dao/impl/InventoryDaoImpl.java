package com.sanisoft.ecount.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.entity.Inventory;

@Repository("InventaryDao")
public class InventoryDaoImpl extends EcountDao implements InventoryDao{
	@Override
	public Inventory get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Inventory inventory=session.get(Inventory.class, id);
		return inventory;
	}
	@Override
	public List<Object[]> getAllForDropDown() {
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select ir.id, ir.name from Inventory ir");
		if(query==null)
			return new ArrayList<Object[]>();
		return query.list();
	}
	@Override
	public List<Object[]> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query= session.createQuery("select ir.id, ir.name, ir.unitPriceSales, ir.coaSales.id, ir.taxSales.id, "
				+ " ir.unitPricePurchase, ir.coaPurchase.id, ir.taxPurchase.id, irb.balance, irb.qty, ir.code,"
				+ " ir.coaInv.id, ir.salesFlag, ir.purchaseFlag, ir.type, irb.id, "
				+ " ir.createdBy, ir.createdDate, irb.createdBy, irb.createdDate "
				+ " from Inventory ir inner join InventoryBalance irb on irb.inv.id = ir.id");
		if(query==null)
			return new ArrayList<Object[]>();
		return query.list();
	}
	@Override
	public List<Inventory> getAllInv() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from Inventory inv inner join fetch inv.invBalance");
		return query.list();
	}
	@Override
	public int create(Inventory inv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(inv);
		return inv.getId();
	}
	@Override
	public int update(Inventory inv) {
		Session session = sessionFactory.getCurrentSession();
		session.update(inv);
		return EcountConstant.SUCCESS;
	}
	@Override
	public int delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Inventory inv = session.get(Inventory.class, id);
		session.delete(inv);
		return EcountConstant.SUCCESS;
	}

}
