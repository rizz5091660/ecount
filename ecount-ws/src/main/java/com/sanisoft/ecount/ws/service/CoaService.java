package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.model.HttpResponseWS;



public interface CoaService {
	public List<Coa> getAll();
	public Coa init(); 
	public HttpResponseWS create(Coa coa); 
	public HttpResponseWS update(Coa coa); 
	public HttpResponseWS delete(int id);
	
}
 