package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.Inventory;

public interface InventoryDao {
	public Inventory get(int id); 
	public List<Object[]> getAllForDropDown();
	public List<Object[]> getAll();
	public List<Inventory> getAllInv();
	public int create(Inventory inv);
	public int update(Inventory inv);
	public int delete(int id);

	
}
