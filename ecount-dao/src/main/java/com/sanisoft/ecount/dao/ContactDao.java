package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.SelectItem;


public interface ContactDao {
	public CustomerSupplier get(int id); 
	public int create(CustomerSupplier custSupp);
	public List<CustomerSupplier> getAll(String type);
	public int delete(CustomerSupplier custSupp);
	public int update(CustomerSupplier custSupp); 
	public List<Object[]> getAllForDropDown(); 
	public List<SelectItem> countType(); 
}
