package com.sanisoft.ecount.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.StageDao;
import com.sanisoft.ecount.entity.Stage;

@Repository("StageDao")
public class StageDaoImpl extends EcountDao implements StageDao {

	@Override
	public Stage get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Stage stage = session.get(Stage.class, id);
		return stage;
	} 

}
