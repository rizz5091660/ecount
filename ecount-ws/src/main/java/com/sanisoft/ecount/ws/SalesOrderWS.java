package com.sanisoft.ecount.ws;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanisoft.ecount.entity.SalesOrder;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.SalesOrderService;


@RestController
@RequestMapping("/so")
public class SalesOrderWS {
	@Autowired
	SalesOrderService soService;
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/get",method=RequestMethod.GET)
	@ResponseBody
	public SalesOrder get(@RequestParam(value="id")int id) {
		return soService.get(id);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	@ResponseBody
	public SalesOrder countSalesGroupByStage() {
		SalesOrder so = new SalesOrder();
		soService.countSalesGroupByStage(so);
		return so;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public List<SalesOrder> salesOrderlist(@RequestParam(value="stg") int stageId,@RequestParam(value="typ") String type){
		return soService.getByStage(stageId,type);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/update_stg",method=RequestMethod.POST)
	@ResponseBody
	public HttpResponseWS updateStage(@RequestBody SalesOrder so){
		return soService.updateStage(so);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/create",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public HttpResponseWS create(@RequestBody SalesOrder so, @RequestHeader HttpHeaders hdr){
		return soService.create(so);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@ResponseBody
	public SalesOrder init() {
		return soService.init();
	}
	
}
