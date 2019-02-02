package com.sanisoft.ecount.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.dao.PurchaseOrderDao;
import com.sanisoft.ecount.dao.StageDao;
import com.sanisoft.ecount.dao.ContactDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.PurchaseOrder;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.PurchaseOrderService;
import com.sanisoft.ecount.ws.util.EcountUtil;


@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	@Autowired
	private PurchaseOrderDao poDao;
	@Autowired
	private StageDao stageDao;
	@Autowired
	private ContactDao supplierDao;
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private TaxDao taxDao;
	@Autowired
	private CoaDao coaDao;
	
	@Override
	public PurchaseOrder init() { 
		PurchaseOrder po = new PurchaseOrder();
		po.setPoCode(EcountUtil.generateRunningNumber(EcountUtil.PURCHASE,poDao.count()));
		po.setCustSupps(EcountUtil.populateDropDown(supplierDao.getAllForDropDown()));
		po.setInventories(EcountUtil.populateDropDownInventory(inventoryDao.getAll()));
		po.setTaxes(EcountUtil.populateDropDown(taxDao.getAllForDropDown()));
		po.setCoas(EcountUtil.populateDropDown(coaDao.getAllForDropDown()));
		return po;
	}
	
	@Override
	public HttpResponseWS create(PurchaseOrder purchaseOrder) {
		int count = 0;
		try {
			purchaseOrder.normalizeRequest();
			purchaseOrder.setStage(stageDao.get(purchaseOrder.getStage().getId()));
			purchaseOrder.setCustSupp(supplierDao.get(purchaseOrder.getSuppId())); 
			count=poDao.create(purchaseOrder);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Create");
	}

	@Override
	public HttpResponseWS updateStageFlow(int id, int stageId) {
		int count = 0;
		try {
			 count = poDao.updateStageFLow(id, stageId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");
	}
	
	@Override
	public HttpResponseWS updateStage(PurchaseOrder po) {
		int count = 0;
		try {
			count= poDao.updateStage(po.getPoIds(),po.getStage().getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");

	}

	@Override
	public HttpResponseWS update(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public PurchaseOrder get(int id) {
		return poDao.get(id);
	}

	@Override
	public void countPurchaseGroupByStage(PurchaseOrder po) {
		poDao.countPurchaseGroupByStage(po);
		
	}

	@Override
	public List<PurchaseOrder> getByStage(int id, String type) {
		return poDao.getByStage(id,type);
	}

}
