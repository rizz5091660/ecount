package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.model.SelectItem;

public interface ContactService {
	public CustomerSupplier get(String id);
	public HttpResponseWS create(CustomerSupplier custSupp); 
	public List<CustomerSupplier> getAllSupplier(String type);	
	public HttpResponseWS delete(int id);
	public HttpResponseWS update(CustomerSupplier custSupp);
	public List<SelectItem> countType(); 
}
 