package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.SalesOrder;
import com.sanisoft.ecount.entity.SalesOrderDetail;
import com.sanisoft.ecount.entity.SalesOrderTransaction;

public interface SalesOrderDao {
	public int create(SalesOrder salesOrder);
	public int update(SalesOrder salesOrder);
	public int delete (int id);
	public int updateStageFLow(int id, int stageId);
	public SalesOrder get(int id); 
	public List<SalesOrder> getAll();
	public void countSalesGroupByStage(SalesOrder so);
	public List<SalesOrder> getByStage(int id, String type);
	public int updateStage(List<Integer> ids, int stageId);
	public long count();
	public SalesOrderDetail getSodInfo(SalesOrderDetail sod);
	public int createSod(SalesOrderDetail sod);
	public int createSoTransaction(SalesOrderTransaction soTxn);
}
