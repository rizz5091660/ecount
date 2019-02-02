package com.sanisoft.ecount.dao;

import java.util.List;

import com.sanisoft.ecount.entity.PurchaseOrder;

public interface PurchaseOrderDao {
	public int create(PurchaseOrder purchaseOrder);
	public int update(PurchaseOrder purchaseOrder);
	public int delete (int id);
	public int updateStageFLow(int id, int stageId);
	public PurchaseOrder get(int id);
	public List<PurchaseOrder> getAll();
	public void countPurchaseGroupByStage(PurchaseOrder so);
	public List<PurchaseOrder> getByStage(int id, String type);
	public int updateStage(List<Integer> ids, int stageId);
	public long count();
}
