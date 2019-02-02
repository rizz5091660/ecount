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

import com.sanisoft.ecount.entity.Inventory;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.InventoryService;

@RestController
@RequestMapping("/inv") 
public class InventoryWS {
	@Autowired
	InventoryService inventoryService;

	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public Inventory init() {
		return inventoryService.init();
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public List<Inventory> getAll() {
		return inventoryService.getAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public HttpResponseWS create(@RequestBody Inventory inv){
		return inventoryService.create(inv);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping (value="/update",method=RequestMethod.PUT)
	@ResponseBody 
	public HttpResponseWS update(@RequestBody Inventory inv){
		return inventoryService.update(inv);
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public HttpResponseWS delete(@RequestParam(value="id") int id) {
		return inventoryService.delete(id); 

	}
	
}
