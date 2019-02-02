package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.PurchaseOrder;
import com.sanisoft.ecount.model.HttpResponseWS;

public interface PurchaseOrderService {
	public HttpResponseWS create(PurchaseOrder purchaseOrder);
	public HttpResponseWS update(PurchaseOrder purchaseOrder);
	public HttpResponseWS updateStageFlow(int id, int stageId);
	public PurchaseOrder get(int id);
	public void countPurchaseGroupByStage(PurchaseOrder po);
	public List<PurchaseOrder> getByStage(int id, String type);
	public HttpResponseWS updateStage(PurchaseOrder po);
	public PurchaseOrder init();
}
 