package com.sanisoft.ecount.ws.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.model.SelectItem;

public class EcountUtil {
	
	public static String INVOICE	= "INV";
	public static String QUOTATION	= "QTN";
	public static String PURCHASE 	= "PCS";
	public static String BILL 		= "BIL";
	
	public static HttpResponseWS generateWSResponse(int id,String type) { 
		HttpResponseWS resp = new HttpResponseWS();
		resp.setValue(id);		
		if(id!=EcountConstant.FAIL) {
			resp.setStatus(200);
			resp.setMessage(type);
		}else {
			resp.setStatus(500);	
			resp.setMessage("System error");
		}
		return resp;
	}
	
	public static void normalizeDataEntry(Object object) {
		if(object instanceof CustomerSupplier) {
			CustomerSupplier custSupp = (CustomerSupplier)object;
			custSupp.setCreatedBy("Rizky");
			custSupp.setCreatedDate(new Date());
			custSupp.setModifiedBy("Rizky");
			custSupp.setModifiedDate(new Date());
			if(custSupp.getAddress()!=null) {
				custSupp.getAddress().setCreatedBy("Rizky");
				custSupp.getAddress().setCreatedDate(new Date());
				custSupp.getAddress().setModifiedBy("Rizky");
				custSupp.getAddress().setModifiedDate(new Date());
			}
		}
	}
	
	public static List<SelectItem> populateDropDown(List<Object[]> results){
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		selectItems.add(new SelectItem("Select","0"));
		for(Object object[]:results) {
			SelectItem dd = new SelectItem();
			dd.setValue((String.valueOf(object[0])));
			dd.setLabel(String.valueOf(object[1]));
			if(object.length>2) {
				dd.setValue2(String.valueOf(object[2]));
			}
			selectItems.add(dd);
		}
		return selectItems;
	}
	
	public static List<SelectItem> populateDropDownInventory(List<Object[]> results){
		List<SelectItem> inventories = new ArrayList<SelectItem>();
		for(Object object[]:results) {
			SelectItem ir = new SelectItem();
			//select ir.id, ir.name, ir.unitPriceSales, ir.coaSales.id, ir.taxSales.id, 
			//ir.unitPricePurchase, ir.coaPurchase.id, ir.taxPurchase.id from Inventory ir
			ir.setValue(String.valueOf(object[0]));
			ir.setLabel(String.valueOf(object[1]));
			ir.setValue2(String.valueOf(object[2]));
			ir.setValue3(String.valueOf(object[3]));
			ir.setValue4(String.valueOf(object[4]));
			ir.setValue5(String.valueOf(object[5]));
			ir.setValue6(String.valueOf(object[6]));
			ir.setValue7(String.valueOf(object[7]));
			inventories.add(ir);
		}
		return inventories;
	}
	
	public static String generateRunningNumber(String type, long count) {
		if(INVOICE.equals(type)) {
			return INVOICE+"-"+String.format("%010d", count);
		}
		
		return "";
	}

}
 