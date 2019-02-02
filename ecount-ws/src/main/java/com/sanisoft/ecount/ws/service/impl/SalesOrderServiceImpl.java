package com.sanisoft.ecount.ws.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaBalanceDao;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.ContactDao;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.dao.SalesOrderDao;
import com.sanisoft.ecount.dao.StageDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.dao.TransactionDao;
import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.entity.CoaBalance;
import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.entity.SalesOrder;
import com.sanisoft.ecount.entity.SalesOrderDetail;
import com.sanisoft.ecount.entity.SalesOrderTransaction;
import com.sanisoft.ecount.entity.Stage;
import com.sanisoft.ecount.entity.Transaction;
import com.sanisoft.ecount.entity.TransactionDetail;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.SalesOrderService;
import com.sanisoft.ecount.ws.util.EcountMathUtil;
import com.sanisoft.ecount.ws.util.EcountUtil;


@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService{
	@Autowired
	private SalesOrderDao soDao;
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
	@Autowired
	private CoaBalanceDao coaBalanceDao;
	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public HttpResponseWS create(SalesOrder salesOrder) {
		int count = 0;
		try {
			salesOrder.normalizeRequest();
			salesOrder.setStage(new Stage(salesOrder.getStage().getId()));
			salesOrder.setCustSupp(new CustomerSupplier(salesOrder.getCustId())); 
			salesOrder.setTotalAmount(new BigDecimal(0));
			salesOrder.setTotalTaxAmount(new BigDecimal(0));
			salesOrder.setTotalAmountCOGS(new BigDecimal(0));
			salesOrder.setTotalAmountNoTax(new BigDecimal(0));
			for(SalesOrderDetail sod : salesOrder.getSods()) {
				soDao.getSodInfo(sod);
				calculate(salesOrder,sod);
			}
			count=soDao.create(salesOrder);			
			//if(salesOrder.getStage().getId()==EcountConstant.STAGE_AWPAYMENT_ID) {
			CoaBalance coaBalance=coaBalanceDao.getByCoaIdMontYear(EcountConstant.COA_CASH,1,2019);
			BigDecimal newBalance = EcountMathUtil.add(coaBalance.getBalance(), salesOrder.getTotalAmount());
			coaBalance.setBalance(newBalance);				
			coaBalanceDao.update(coaBalance);
			Transaction trxn = new Transaction("", "", salesOrder.getTotalAmount(), salesOrder.getTrxnDate(), "Rizky", new Date());
			transactionDao.create(trxn);			
			for(SalesOrderDetail sod : salesOrder.getSods()) {
				sod.setSo(salesOrder);
				soDao.createSod(sod);				
				TransactionDetail trxDetail =  new TransactionDetail(trxn, new Coa(EcountConstant.COA_CASH), 1, sod.getTxnAmount(), new BigDecimal(0),"Rizky", new Date()); 
				transactionDao.createDetail(trxDetail);						
				trxDetail =  new TransactionDetail(trxn, new Coa(sod.getInventory().getCoaSales().getId()), 2, new BigDecimal(0), sod.getTxnAmountNoTax(),"Rizky", new Date()); 
				transactionDao.createDetail(trxDetail);
				trxDetail =  new TransactionDetail(trxn, new Coa(EcountConstant.COA_TAX_SALES), 3, new BigDecimal(0), sod.getTaxAmount(),"Rizky", new Date()); 
				transactionDao.createDetail(trxDetail);
				trxDetail =  new TransactionDetail(trxn, new Coa(EcountConstant.COA_COGS), 4, sod.getTxnCogs(), new BigDecimal(0),"Rizky", new Date()); 
				transactionDao.createDetail(trxDetail);
				trxDetail =  new TransactionDetail(trxn, new Coa(sod.getInventory().getCoaInv().getId()), 5, new BigDecimal(0),sod.getTxnCogs(),"Rizky", new Date()); 
				transactionDao.createDetail(trxDetail);
			}
			soDao.createSoTransaction(new SalesOrderTransaction(salesOrder.getId(),trxn.getId(),"Rizky",new Date()));
			//}			
		}catch(Exception e) { 
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Create");
	}
	
	
	private void calculate(SalesOrder salesOrder,SalesOrderDetail sod) {		
			try {
				BigDecimal amountSod =  EcountMathUtil.multiply(sod.getInventory().getUnitPriceSales(),new BigDecimal(sod.getQuantity()));
				BigDecimal taxAmountSod = EcountMathUtil.multiply(amountSod,new BigDecimal(sod.getTax().getPercentage()/100));
				BigDecimal totalAmountSod =EcountMathUtil.add(amountSod, taxAmountSod);				
				BigDecimal cogs= EcountMathUtil.multiply(sod.getInventory().getUnitPricePurchase(),new BigDecimal(sod.getQuantity()));	
				sod.setTxnAmount(totalAmountSod);
				sod.setTxnAmountNoTax(amountSod);
				sod.setTaxAmount(taxAmountSod);
				sod.setTxnCogs(cogs);				
				salesOrder.setTotalAmount(EcountMathUtil.add(salesOrder.getTotalAmount(), totalAmountSod));
				salesOrder.setTotalAmountNoTax( EcountMathUtil.add(salesOrder.getTotalAmountNoTax(), amountSod));
				salesOrder.setTotalTaxAmount(EcountMathUtil.add(salesOrder.getTotalTaxAmount(), taxAmountSod));				
				salesOrder.setTotalAmountCOGS(EcountMathUtil.add(salesOrder.getTotalAmountCOGS(), cogs));				
			} catch (Exception e) {				
				e.printStackTrace();
			}
	}
	
	

	@Override
	public HttpResponseWS updateStageFlow(int id, int stageId) {
		int count =0;
		try {
		 count = soDao.updateStageFLow(id, stageId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");
	}

	@Override
	public HttpResponseWS update(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public SalesOrder get(int id) {
		return soDao.get(id);
	}

	@Override
	public void countSalesGroupByStage(SalesOrder so) {
		soDao.countSalesGroupByStage(so);		
	}

	@Override
	public List<SalesOrder> getByStage(int id, String type) {
		return soDao.getByStage(id,type);
	}

	@Override
	public HttpResponseWS updateStage(SalesOrder so) {
		int count = 0;
		try {
			 soDao.updateStage(so.getSalesIds(),so.getStage().getId());
			 count = 1 ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");
	}

	@Override
	public SalesOrder init() { 
		SalesOrder so = new SalesOrder();
		so.setSoCode(EcountUtil.generateRunningNumber(EcountUtil.INVOICE,soDao.count()));
		so.setCustSupps(EcountUtil.populateDropDown(supplierDao.getAllForDropDown()));
		so.setInventories(EcountUtil.populateDropDownInventory(inventoryDao.getAll()));
		so.setTaxes(EcountUtil.populateDropDown(taxDao.getAllForDropDown()));
		so.setCoas(EcountUtil.populateDropDown(coaDao.getAllForDropDown()));
		return so; 
	}

}
