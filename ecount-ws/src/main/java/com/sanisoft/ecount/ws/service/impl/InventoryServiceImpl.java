package com.sanisoft.ecount.ws.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.InventoryBalanceDao;
import com.sanisoft.ecount.dao.InventoryDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.entity.InventoryBalance;
import com.sanisoft.ecount.entity.Tax;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.model.SelectItem;
import com.sanisoft.ecount.ws.service.InventoryService;
import com.sanisoft.ecount.ws.util.EcountUtil;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	InventoryDao inventoryDao; 
	@Autowired
	InventoryBalanceDao inventoryBalanceDao; 
	@Autowired
	CoaDao coaDao;
	@Autowired
	TaxDao taxDao;

	@Override
	public List<Inventory> getAll() {
		List<Inventory> inventories = new ArrayList<Inventory>();
		List<Object[]> objects=inventoryDao.getAll();
		for(Object[] obj:objects) {
			try {
				Inventory inv = new Inventory();
				inv.setId((int) obj[0]);
				inv.setName((String) obj[1]);
				inv.setUnitPriceSales(new BigDecimal(String.valueOf(obj[2])));
				inv.setCode((String) obj[10]);
				inv.setCreatedBy((String) obj[16]);
				inv.setCreatedDate((Date) obj[17]);
				Coa coaInv = new Coa();
				coaInv.setId((int) obj[11]);
				inv.setCoaInv(coaInv);
				inv.setSalesFlag((boolean) obj[12]);
				inv.setPurchaseFlag((boolean) obj[13]);
				inv.setType((String) obj[14]);
				Coa coaSales = new Coa();
				coaSales.setId((int) obj[3]);
				inv.setCoaSales(coaSales);
				Tax taxSales = new Tax();
				taxSales.setId((int) obj[4]);
				inv.setTaxSales(taxSales);
				inv.setUnitPricePurchase(new BigDecimal(String.valueOf(obj[5])));
				Coa coaPurchase = new Coa();
				coaPurchase.setId((int) obj[6]);
				inv.setCoaPurchase(coaPurchase);
				Tax taxPurchase = new Tax();
				taxPurchase.setId((int) obj[7]);
				inv.setTaxPurchase(taxPurchase);
				InventoryBalance irb = new InventoryBalance();
				irb.setId((int) obj[15]);
				irb.setBalance(new BigDecimal(String.valueOf(obj[8])));
				irb.setQty((int)obj[9]);
				irb.setCreatedBy((String) obj[18]);
				irb.setCreatedDate((Date) obj[19]);
				inv.setInvBalance(irb);
				inventories.add(inv);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return inventories;
	}

	@Override
	public Inventory init() {
		Inventory inventory = new Inventory();
		inventory.setCoas(EcountUtil.populateDropDown(coaDao.getAllForDropDown()));
		inventory.setTaxes(EcountUtil.populateDropDown(taxDao.getAllForDropDown()));
		List<SelectItem> cc = new ArrayList<SelectItem>();
		SelectItem si = new SelectItem();
		si.setLabel("Cost Center 1");
		si.setValue("1");
		cc.add(si);
		inventory.setCostCenters(cc);
		cc = new ArrayList<SelectItem>();
		si = new SelectItem();
		si.setLabel("Profit Center 1");
		si.setValue("1");
		cc.add(si);
		inventory.setProfitCenters(cc);
		return inventory;
	}

	@Override
	public HttpResponseWS create(Inventory inv) {
		try {
			inv.normalizeRequest();
			InventoryBalance invBal = inv.getInvBalance();		
			invBal.normalizeRequest();
			if(invBal.getQty()!=0 && inv.getUnitPricePurchase()!=null) {
				double balance = invBal.getQty() * inv.getUnitPricePurchase().doubleValue();
				invBal.setBalance(new BigDecimal(balance));
			}		
			inv.getInvBalance().setInv(inv);
			inventoryDao.create(inv);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(inv.getId(),"Create");
	}

	@Override
	public HttpResponseWS update(Inventory inv) {
		int count=0;
		try {
			inv.setModifiedBy("Rizky");
			inv.setModifiedDate(new Date());
			inv.getInvBalance().setModifiedBy("Rizky");
			inv.getInvBalance().setModifiedDate(new Date());			
			inv.getInvBalance().setInv(inv);			
			count=inventoryDao.update(inv);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Update");
	}

	@Override
	public HttpResponseWS delete(int id) {
		int count=0;
		try {
			count=inventoryDao.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Delete");
	}

}
