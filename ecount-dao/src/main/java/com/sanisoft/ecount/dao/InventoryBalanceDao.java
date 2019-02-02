package com.sanisoft.ecount.dao;

import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.InventoryBalance;

public interface InventoryBalanceDao {
	public InventoryBalance get(int id);
	public int create(InventoryBalance invBalance);
	public int update(Inventory invBalance);
	public int delete(int id);

}
