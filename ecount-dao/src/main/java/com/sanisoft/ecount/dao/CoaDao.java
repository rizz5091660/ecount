package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.Coa;



public interface CoaDao {
	public Coa get(int id);
	public List<Coa> getAll();
	public int create(Coa coa);
	public int update(Coa coa);
	public int delete(int id);
	public List<Object[]> getAllForDropDown();
}
