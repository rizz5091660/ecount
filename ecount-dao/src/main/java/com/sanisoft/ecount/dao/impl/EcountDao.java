package com.sanisoft.ecount.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class EcountDao {	
	@Autowired
	SessionFactory sessionFactory;	
	JdbcTemplate jdbcTemplate;


}
