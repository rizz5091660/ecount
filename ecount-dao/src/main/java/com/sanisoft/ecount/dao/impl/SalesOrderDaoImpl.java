package com.sanisoft.ecount.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.dao.SalesOrderDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.SalesOrder;
import com.sanisoft.ecount.entity.SalesOrderDetail;
import com.sanisoft.ecount.entity.SalesOrderTransaction;
import com.sanisoft.ecount.entity.Stage;
import com.sanisoft.ecount.entity.Tax;

 
@Repository("SalesOrderDao")
public class SalesOrderDaoImpl extends EcountDao implements SalesOrderDao {
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private TaxDao taxDao;
	@Autowired
	private CoaDao coaDao;
	
	@Override
	public int create(SalesOrder salesOrder) {
		Session session = sessionFactory.getCurrentSession();
		session.save(salesOrder);
		return salesOrder.getId();
	}
	
	@Override
	public int createSod(SalesOrderDetail sod) {
		Session session = sessionFactory.getCurrentSession();
		sod.normalizeRequest();			
		session.save(sod);
		return EcountConstant.SUCCESS;
	}
	
	@Override
	public SalesOrderDetail getSodInfo(SalesOrderDetail sod) {		
		Inventory inventory = inventoryDao.get(Integer.valueOf(sod.getInvDD().getValue()));
		Tax tax = taxDao.get(Integer.valueOf(sod.getTaxDD().getValue()));
		Coa coa = coaDao.get(Integer.valueOf(sod.getCoaDD().getValue()));
		sod.setInventory(inventory);
		sod.setTax(tax);
		sod.setCoa(coa);		
		return sod;
	}
	
	
	@Override
	public int update(SalesOrder salesOrder) {
		Session session = sessionFactory.getCurrentSession();
		session.update(salesOrder);
		return EcountConstant.SUCCESS;
	}

	@Override
	public int delete(int id){
		Session session = sessionFactory.getCurrentSession();
		SalesOrder salesOrder = session.get(SalesOrder.class, id);
		session.delete(salesOrder);
		return EcountConstant.SUCCESS;
	}
	
	@Override
	public int createSoTransaction(SalesOrderTransaction soTxn) {
		Session session = sessionFactory.getCurrentSession();
		session.save(soTxn);
		return soTxn.getId();
	}
	

	@Override
	public int updateStageFLow(int id, int stageId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("UPDATE so set stage_id="+stageId+ " where id="+id);
		return query.executeUpdate();
	}

	@Override
	public void countSalesGroupByStage(SalesOrder so) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query query		= session.createQuery("SELECT count(1) as count, stage.id, soType, sum(totalAmount) as totalAmount from SalesOrder group by stage.id, soType");
		List<Object[]> list	= query.list();
		if(list!=null) {
			for(Object[] obj: list) {
				int stageId=(Integer) obj[1];
				String type = (String) obj[2];
				int count =  Integer.valueOf(String.valueOf(obj[0]));
				BigDecimal totalAmt = new BigDecimal(String.valueOf(obj[3]));
				if("Q".equalsIgnoreCase(type)) {
					if(stageId==EcountConstant.STAGE_DRAFT_ID) {
						so.setnDraftQ(count);
						so.setTotalAmtDraftQ(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWAPPROVAL_ID) {
						so.setnAwApprovalQ(count);
						so.setTotalAmtAwApprovalQ(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWPAYMENT_ID) {
						so.setnAwPaymentQ(count);
						so.setTotalAmtAwPaymentQ(totalAmt);
					}else if(stageId==EcountConstant.STAGE_OVERDUE_ID) {
						so.setnOverdueQ(count);
						so.setTotalAmtOverdueQ(totalAmt);
					}else if(stageId==EcountConstant.STAGE_PAID_ID) {
						so.setnPaidQ(count);
						so.setTotalAmtPaidQ(totalAmt);
					}
				}else if("I".equalsIgnoreCase(type)) {
					if(stageId==EcountConstant.STAGE_DRAFT_ID) {
						so.setnDraft(count);
						so.setTotalAmtDraft(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWAPPROVAL_ID) {
						so.setnAwApproval(count);
						so.setTotalAmtAwApproval(totalAmt);
					}else if(stageId==EcountConstant.STAGE_AWPAYMENT_ID) {
						so.setnAwPayment(count);
						so.setTotalAmtAwPayment(totalAmt);
					}else if(stageId==EcountConstant.STAGE_OVERDUE_ID) {
						so.setnOverdue(count);
						so.setTotalAmtOverdue(totalAmt);
					}else if(stageId==EcountConstant.STAGE_PAID_ID) {
						so.setnPaid(count);
						so.setTotalAmtPaid(totalAmt);
					}

				}
				
			}
		}
	}

	@Override
	public List<SalesOrder> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(SalesOrder.class, id);
	}

	@Override
	public List<SalesOrder> getByStage(int id, String type) {
		Session session = sessionFactory.getCurrentSession();
		List<SalesOrder> saless = new ArrayList<SalesOrder>();
		String sql =null;
		if(id==0) {
			sql="select so.id,so.soCode,cs.name,so.trxnDate,so.trxnDate,so.totalTaxAmount,so.referrence,so.stage.name from SalesOrder so inner join CustomerSupplier cs on so.custSupp.id =cs.id where so.soType=:type";			
		}else {
			sql="select so.id,so.soCode,cs.name,so.trxnDate,so.trxnDate,so.totalTaxAmount,so.referrence,so.stage.name from SalesOrder so inner join CustomerSupplier cs on so.custSupp.id =cs.id where so.stage.id = :stageId and so.soType=:type";
		}
		@SuppressWarnings("deprecation")
		Query<Object[]> query = session.createQuery(sql);
		query.setString("type", type);
		if(id!=0) {
			query.setInteger("stageId", id);
		}
		List<Object[]> objects=query.list();
		for(Object[] obj:objects) {
			SalesOrder so = new SalesOrder();
			so.setId((int) obj[0]);
			so.setSoCode((String) obj[1]);
			so.setCustName((String) obj[2]);
			so.setTrxnDate((Timestamp) obj[3]);
			so.setEstDeliveryDate((Timestamp) obj[4]);
			so.setTotalAmount((BigDecimal) obj[5]);
			so.setReferrence((String)obj[6]);
			so.setStage(new Stage(0, (String)obj[7], null, null, null, null));
			saless.add(so);
		}		
		return saless;
	}
	
	@Override
	public int updateStage(List<Integer> ids, int stageId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from SalesOrder where id in (:ids)").setParameterList("ids", ids);
		List<SalesOrder>saless = query.list();		
		for(int i=0;i<saless.size();i++) {
			Stage stage = new Stage();
			stage.setId(stageId);
			SalesOrder so = saless.get(i);				
			so.setStage(stage);
			session.update(so);

		}	
		return EcountConstant.SUCCESS;
	}

	@Override
	public long count() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(so.id) from SalesOrder so");
		long count = (long) query.iterate().next() + 1;
		return count;
	}
	
	
}
