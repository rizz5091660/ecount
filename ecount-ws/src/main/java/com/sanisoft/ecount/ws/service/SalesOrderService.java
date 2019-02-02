package com.sanisoft.ecount.ws.service;

import java.util.List;

import com.sanisoft.ecount.entity.SalesOrder;
import com.sanisoft.ecount.model.HttpResponseWS;

public interface SalesOrderService {
	public HttpResponseWS create(SalesOrder salesOrder);
	public HttpResponseWS update(SalesOrder salesOrder);
	public HttpResponseWS updateStageFlow(int id, int stageId);
	public SalesOrder get(int id);
	public void countSalesGroupByStage(SalesOrder so);
	public List<SalesOrder> getByStage(int id, String type); 
	public HttpResponseWS updateStage(SalesOrder so);
	public SalesOrder init();
}
 