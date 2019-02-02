package com.sanisoft.ecount.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.dao.PurchaseOrderDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.PurchaseOrder;
import com.sanisoft.ecount.entity.PurchaseOrderDetail;
import com.sanisoft.ecount.entity.Stage;
import com.sanisoft.ecount.entity.Tax;

 
@Repository("PurchaseOrderDao")
public class PurchaseOrderDaoImpl extends EcountDao implements PurchaseOrderDao {
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private TaxDao taxDao;
	@Autowired
	private CoaDao coaDao;
	
	@Override
	public int create(PurchaseOrder purchaseOrder) {
		Session session = sessionFactory.getCurrentSession();
		long id=(Long) session.save(purchaseOrder);
		for(PurchaseOrderDetail pod: purchaseOrder.getPods()) {
			Inventory inventory = inventoryDao.get(Integer.valueOf(pod.getInvDD().getValue()));
			Tax tax = taxDao.get(Integer.valueOf(pod.getTaxDD().getValue()));
			Coa coa = coaDao.get(Integer.valueOf(pod.getCoaDD().getValue()));
			pod.setPo(purchaseOrder);
			pod.setInventory(inventory);
			pod.setTax(tax);
			pod.setCoa(coa);
			pod.normalizeRequest();			
			session.save(pod); 
			
		}
		return purchaseOrder.getId();
	}

	@Override
	public int update(PurchaseOrder purchaseOrder) {
		Session session = sessionFactory.getCurrentSession();
		session.update(purchaseOrder);
		return EcountConstant.SUCCESS;
	}

	@Override
	public int delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		PurchaseOrder purchaseOrder = session.get(PurchaseOrder.class, id);
		session.delete(purchaseOrder);
		return EcountConstant.SUCCESS;
	}

	@Override
	public int updateStageFLow(int id, int stageId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("UPDATE po set stage_id="+stageId+ " where id="+id);
		return query.executeUpdate();
	}

	@Override
	public void countPurchaseGroupByStage(PurchaseOrder po) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query query		= session.createQuery("SELECT count(1) as count, stage.id, poType, sum(totalAmount) as totalAmount from PurchaseOrder group by stage.id, poType");
		List<Object[]> list	= query.list();
		if(list!=null) {
			for(Object[] obj: list) {
				int stageId=(Integer) obj[1];
				String type = (String) obj[2];
				int count =  Integer.valueOf(String.valueOf(obj[0]));
				BigDecimal totalAmt = new BigDecimal(String.valueOf(obj[3]));
				if("B".equalsIgnoreCase(type)) {
					if(stageId==EcountConstant.STAGE_DRAFT_ID) {
						po.setnDraft(count);
						po.setTotalAmtDraft(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWAPPROVAL_ID) {
						po.setnAwApproval(count);
						po.setTotalAmtAwApproval(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWPAYMENT_ID) {
						po.setnAwPayment(count);
						po.setTotalAmtAwPayment(totalAmt);
					}else if(stageId==EcountConstant.STAGE_OVERDUE_ID) {
						po.setnOverdue(count);
						po.setTotalAmtOverdue(totalAmt);
					}else if(stageId==EcountConstant.STAGE_PAID_ID) {
						po.setnPaid(count);
						po.setTotalAmtPaid(totalAmt);
					}
				}else if("P".equalsIgnoreCase(type)) {
					if(stageId==EcountConstant.STAGE_DRAFT_ID) {
						po.setnDraft(count);
						po.setTotalAmtDraftPO(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWAPPROVAL_ID) {
						po.setnAwApproval(count);
						po.setTotalAmtAwApprovalPO(totalAmt);
					}else if(stageId==EcountConstant.STAGE_APPROVED_ID) {
						po.setnApprovedPO(count);
						po.setTotalAmtApprovedPO(totalAmt);
					}else if(stageId==EcountConstant.STAGE_BILLED_ID) {
						po.setnBilledPO(count);
						po.setTotalAmtBilledPO(totalAmt);
					}

				}
				
			}
		}
	}

	@Override
	public List<PurchaseOrder> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrder get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PurchaseOrder> getByStage(int id, String type) {
		Session session = sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchase = new ArrayList<PurchaseOrder>();
		String sql =null;
		if(id==0) {
			sql="select po.id,po.soCode,cs.name,po.trxnDate,po.trxnDate,po.totalTaxAmount from PurchaseOrder po inner join CustomerSupplier cs on po.custSupp.id =cs.id where po.poType=:type";			
		}else {
			sql="select po.id,po.soCode,cs.name,po.trxnDate,po.trxnDate,po.totalTaxAmount from PurchaseOrder po inner join CustomerSupplier cs on po.custSupp.id =cs.id where po.stage.id = :stageId and po.poType=:type";
		}
		@SuppressWarnings("deprecation")
		Query<Object[]> query = session.createQuery(sql);
		query.setString("type", type);
		if(id!=0) {
			query.setInteger("stageId", id);
		}
		List<Object[]> objects=query.list();
		for(Object[] obj:objects) {
			PurchaseOrder po = new PurchaseOrder();
			po.setId((Integer) obj[0]);
			po.setPoCode((String) obj[1]);
			po.setSuppName((String) obj[2]);
			po.setTrxnDate((Timestamp) obj[3]);
			po.setEstDeliveryDate((Timestamp) obj[4]);
			po.setTotalAmount((BigDecimal) obj[5]);
			purchase.add(po);
		}		
		return purchase;
	}
	
	@Override
	public int updateStage(List<Integer> ids, int stageId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PurchaseOrder where id in (:ids)").setParameterList("ids", ids);
		List<PurchaseOrder>purchases = query.list();		
		for(int i=0;i<purchases.size();i++) {
			Stage stage = new Stage();
			stage.setId(stageId); 
			PurchaseOrder po = purchases.get(i);				
			po.setStage(stage);
			session.update(po);
		}
		return EcountConstant.SUCCESS;
	}

	@Override
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(po.id) from PurchaseOrder po");
		long count = (long) query.iterate().next() + 1;
		return count;
	}
	
	
}
