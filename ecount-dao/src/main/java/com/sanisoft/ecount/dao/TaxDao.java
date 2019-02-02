package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.Tax;

public interface TaxDao {
	public Tax get(int id);
	public List<Object[]> getAllForDropDown();
}
