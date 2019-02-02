package com.sanisoft.ecount.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.ContactDao;
import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.SelectItem;

@Repository("SupplierDao") 
public class ContactDaoImpl extends EcountDao implements ContactDao{
 
	@Override
	public CustomerSupplier get(int id) { 
		Session session = sessionFactory.getCurrentSession();
		CustomerSupplier customerSupplier =session.get(CustomerSupplier.class, id);
		return customerSupplier;
	}
	
	@Override
	public int create(CustomerSupplier custSupp) {
		Session session = sessionFactory.getCurrentSession();
		session.save(custSupp);
		return custSupp.getId();
	}


	@Override
	public int delete(CustomerSupplier custSupp){ 
		Session session = null;
		session = sessionFactory.getCurrentSession();
		session.delete(custSupp);
		return 1;
	}

	@Override
	public int update(CustomerSupplier custSupp){
		Session session = sessionFactory.getCurrentSession();
		session.update(custSupp);
		return custSupp.getId();
	}

	@Override
	public List<CustomerSupplier>getAll(String type){
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerSupplier> query = null;
		if("all".equals(type)) {
			query = session.createQuery("from CustomerSupplier c");
		}else {
			query = session.createQuery("from CustomerSupplier c where c.type=:type");	
			query.setParameter("type", type);
		}
		List<CustomerSupplier> custSupps= query.list();
		return custSupps;
	}

	
	@Override
	public List<Object[]> getAllForDropDown(){
		Session session =sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select cs.id, cs.name, concat(cs.address.street,', ',cs.address.city,', ',cs.address.state,', ',cs.address.zip,', ',cs.address.country)  as address  from CustomerSupplier cs");
		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public List<SelectItem> countType() {
		List<SelectItem> list= new ArrayList<SelectItem>();
		SelectItem si = new SelectItem();
		si.setLabel("All (0)");
		si.setValue("all");
		si.setValue2("All");
		list.add(si);

		si = new SelectItem();
		si.setLabel("Customer (0)");
		si.setValue("c");
		si.setValue2("Customer");
		list.add(si);
		
		si = new SelectItem();
		si.setLabel("Supplier (0)");
		si.setValue("s");
		si.setValue2("Supplier");
		list.add(si);
		
		si = new SelectItem();
		si.setLabel("Employee (0)"); 
		si.setValue("e");
		si.setValue2("Employee");
		list.add(si);
		
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createQuery("select cs.type, count(1) as count from CustomerSupplier cs group by cs.type");
		List<Object[]> objs = query.list();
		long total=0;
		for(Object object[]:objs) {
			for(SelectItem sim : list) {
				if(sim.getValue().equals(String.valueOf(object[0]))) {
					sim.setLabel(sim.getValue2()+" ("+Long.valueOf(String.valueOf(object[1]))+")");
				}
			}
			total = total + Long.valueOf(String.valueOf(object[1]));
		}
		list.get(0).setLabel("All ("+total+")");		
		return list;
	}
}
