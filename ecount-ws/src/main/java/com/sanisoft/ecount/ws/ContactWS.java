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

import com.sanisoft.ecount.entity.CustomerSupplier;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.ContactService;


@RestController
@RequestMapping("/contact") 
public class ContactWS {
	@Autowired	
	private ContactService customerSupplierService;

	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080" })
	@RequestMapping(value="/get",method=RequestMethod.GET)
	@ResponseBody
	public CustomerSupplier get(@RequestParam(value="id") String id) {
		return customerSupplierService.get(id);
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/create",method=RequestMethod.POST) 
	@ResponseBody
	public HttpResponseWS create(@RequestBody CustomerSupplier custSupp) {
		return customerSupplierService.create(custSupp);
	}

	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public List<CustomerSupplier> getAllSupplier(@RequestParam(value="type") String type) {
		return customerSupplierService.getAllSupplier(type);
	} 
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public HttpResponseWS delete(@RequestParam(value="id") int id) {
		return customerSupplierService.delete(id); 

	}

	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping (value="/update",method=RequestMethod.PUT)
	@ResponseBody 
	public HttpResponseWS update(@RequestBody CustomerSupplier custSupp){
		return customerSupplierService.update(custSupp);
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@ResponseBody
	public CustomerSupplier init() {
		CustomerSupplier contact = new CustomerSupplier();
		contact.setCustSupps(customerSupplierService.getAllSupplier("all"));
		contact.setCountTypes(customerSupplierService.countType());
		return contact;
	} 
}
