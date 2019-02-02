package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.Organization;


public interface OrganizationDao {
	public Organization getById(int id);
	public int create(Organization organization);
	public List<Organization> getAll();
	public int deleteById(int id);
}
