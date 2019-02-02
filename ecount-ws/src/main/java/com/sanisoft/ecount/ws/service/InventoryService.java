package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.model.HttpResponseWS;

public interface InventoryService {
	public List<Inventory> getAll();
	public Inventory init();
	public HttpResponseWS create(Inventory inv); 
	public HttpResponseWS update(Inventory inv); 
	public HttpResponseWS delete(int id);
}
