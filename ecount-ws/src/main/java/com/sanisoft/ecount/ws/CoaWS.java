package com.sanisoft.ecount.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.CoaService;

@RestController
@RequestMapping("/coa")
public class CoaWS {
	@Autowired  
	CoaService coaService;
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public List<Coa> getAll() {
		return coaService.getAll();
	}
	 
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@ResponseBody
	public Coa getCoaLevelDropDown() { 
		return coaService.init();
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public HttpResponseWS create(@RequestBody Coa coa) {
		return coaService.create(coa);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	@ResponseBody
	public HttpResponseWS update(@RequestBody Coa coa){
		return coaService.update(coa);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"  })
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public HttpResponseWS delete(@RequestParam(value="id") int id){
		return coaService.delete(id);
	}	

}
