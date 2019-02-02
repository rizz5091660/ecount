package com.sanisoft.ecount.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanisoft.ecount.entity.PurchaseOrder;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.PurchaseOrderService;


@RestController
@RequestMapping("/po")
public class PurchaseOrderWS {
	@Autowired
	PurchaseOrderService poService;
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	@ResponseBody
	public PurchaseOrder countSalesGroupByStage() {
		PurchaseOrder po = new PurchaseOrder();
		poService.countPurchaseGroupByStage(po);
		return po;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrder> purchaseOrderlist(@RequestParam(value="stg") int stageId,@RequestParam(value="typ") String type){
		return poService.getByStage(stageId,type);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/update_stg",method=RequestMethod.POST)
	@ResponseBody
	public HttpResponseWS updateStage(@RequestBody PurchaseOrder po){
		return poService.updateStage(po);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public HttpResponseWS create(@RequestBody PurchaseOrder po){
		return poService.create(po);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@ResponseBody
	public PurchaseOrder init() {
		return poService.init();
	}
	
}
