package com.sanisoft.ecount.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.dao.ContactDao;
import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.model.SelectItem;
import com.sanisoft.ecount.ws.service.ContactService;
import com.sanisoft.ecount.ws.util.EcountUtil;


@Service("CustSuppService")
@Transactional
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactDao supplierDao;

	public CustomerSupplier get(String id){ 
		return supplierDao.get(Integer.valueOf(id));
	}  

	public HttpResponseWS create(CustomerSupplier contact){
		int count= 0;
		try {
			contact.normalizeRequest();
			count= supplierDao.create(contact);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Create");

	}

	@Override
	public List<CustomerSupplier> getAllSupplier(String type){
		return supplierDao.getAll(type);
	} 

	@Override
	public HttpResponseWS delete(int id){
		int count= 0;
		try {
			CustomerSupplier contact = supplierDao.get(id);
			count = supplierDao.delete(contact);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Delete");
	} 

	@Override
	public HttpResponseWS update(CustomerSupplier contact){
		int count= 0;
		try {
			count=supplierDao.update(contact);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");
	}

	@Override
	public List<SelectItem> countType() {
		return supplierDao.countType();
	}


}
