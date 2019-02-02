package com.sanisoft.ecount.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.entity.AccountDetailType;
import com.sanisoft.ecount.entity.AccountType;
import com.sanisoft.ecount.entity.Coa;


@Repository("CoaDao")
public class CoaDaoImpl extends EcountDao implements CoaDao{
	
	/*
	@Override
	public List<Coa>getAll(){
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Object[]> query = session.createQuery("select c.id, c.accountType.id, c.accountType.name, c.coaCd, c.name, c.description, c.taxObj.id, "
				+ " c.branch.id, c.custSupp.id, c.division.id, c.customLevel.id, c.taxObj.name from Coa c order by c.coaCd");
		List<Object[]> objects = query.list();	
		List<Coa> coas = new ArrayList<Coa>();
		for(Object[] obj :objects) {
			Coa coa = new Coa();
			coa.setId((int) obj[0]);
			coa.setL1AccountType((int) obj[1]);
			coa.setL1AccountTypName((String) obj[2]);
			coa.setCoaCd((String) obj[3]);
			coa.setName((String) obj[4]);
			coa.setDescription((String) obj[5]);
			coa.setTax((int) obj[6]);
			coa.setL2Branch((int)obj[7]);
			coa.setL3CustSupp((int)obj[8]);
			coa.setL4Division((int)obj[9]);
			coa.setL5Custom((int)obj[10]);
			coa.setTaxName((String)obj[11]);
			coas.add(coa);
		} 
		return coas;
	}
*/
	@Override
	public List<Coa> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id, c.name, c.description, "
				+ " c.accountType.id,  c.accountType.name, c.accountDetailType.id, c.accountDetailType.name  from Coa c");
		List<Object[]> objects = query.list();	
		List<Coa> coas = new ArrayList<Coa>();
		for(Object[] obj : objects) {
			Coa coa = new Coa();
			coa.setId((int)obj[0]);
			coa.setName((String)obj[1]);
			coa.setDescription((String)obj[2]);
			AccountType at = new AccountType();
			at.setId((int)obj[3]);
			at.setName((String) obj[4]);
			coa.setAccountType(at);
			AccountDetailType adt = new AccountDetailType();
			adt.setId((int) obj[5]);
			adt.setName((String) obj[6]);
			coa.setAccountDetailType(adt);
			coas.add(coa);
		}
		return coas;
	}
	
	@Override
	public int create(Coa coa) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coa);
		return coa.getId();
	}

	@Override
	public int update(Coa coa) {
		Session session = sessionFactory.getCurrentSession();
		session.update(coa);
		return EcountConstant.SUCCESS;
	}

	@Override
	public int delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Coa coa = session.find(Coa.class, id);
		session.delete(coa);
		return EcountConstant.SUCCESS;
	}

	@Override
	public List<Object[]> getAllForDropDown() {
		Session session = sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		Query<Object[]> query = session.createQuery("select c.id, concat(c.name,' - ',c.description) from Coa c");
		List <Object[]> list = query.list();
		return list;
	}

	@Override
	public Coa get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Coa coa  = session.get(Coa.class, id);
		return coa;
	}

}
