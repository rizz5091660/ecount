package com.sanisoft.ecount.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanisoft.ecount.dao.AccountDetailTypeDao;
import com.sanisoft.ecount.dao.AccountTypeDao;
import com.sanisoft.ecount.dao.BranchDao;
import com.sanisoft.ecount.dao.CoaBalanceDao;
import com.sanisoft.ecount.dao.CoaDao;
import com.sanisoft.ecount.dao.ContactDao;
import com.sanisoft.ecount.dao.DivisionDao;
import com.sanisoft.ecount.dao.TaxDao;
import com.sanisoft.ecount.entity.Coa;
import com.sanisoft.ecount.entity.CoaBalance;
import com.sanisoft.ecount.model.HttpResponseWS;
import com.sanisoft.ecount.ws.service.CoaService;
import com.sanisoft.ecount.ws.util.EcountUtil;


@Service("CoaService")
@Transactional
public class CoaServiceImpl implements CoaService{ 
	@Autowired
	CoaDao coaDao;
	@Autowired
	CoaBalanceDao coaBalanceDao;
	@Autowired
	AccountTypeDao accountTypeDao;
	@Autowired
	AccountDetailTypeDao accountDetailTypeDao;
	@Autowired
	BranchDao branchDao;
	@Autowired
	DivisionDao divisionDao;
	@Autowired
	ContactDao supplierDao;
	@Autowired
	TaxDao taxDao;
	
	@Override
	public List<Coa> getAll() {
		return coaDao.getAll();
	}

	@Override
	public Coa init() {
		Coa coa =new Coa();
		coa.setAccountTypes(EcountUtil.populateDropDown(accountTypeDao.getAllForDropDown()));
		/*
		coa.setBranches( EcountUtil.populateDropDown(branchDao.getAllForDropDown()));
		coa.setDivisions(EcountUtil.populateDropDown(divisionDao.getAllForDropDown()));
		coa.setCustSupps(EcountUtil.populateDropDown(supplierDao.getAllForDropDown()));
		*/
		coa.setAccountDetailTypes(EcountUtil.populateDropDown(accountDetailTypeDao.getAllForDropDown()));
		coa.setTaxes(EcountUtil.populateDropDown(taxDao.getAllForDropDown()));
		coa.setCoas(coaDao.getAll());
		return coa;
	}

	@Override
	public HttpResponseWS create(Coa coa) {
		int count = 0;
		try {
			coa.normalizeRequest();
			coaDao.create(coa);
			CoaBalance coaBalance = new CoaBalance();
			coaBalance.setBalance(coa.getBalance());
			coaBalance.setCoa(coa);
			coaBalance.setAsOf(coa.getAsOf());
			coaBalance.normalizeRequest();
			count = coaBalanceDao.create(coaBalance);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EcountUtil.generateWSResponse(count,"Create");
	}

	@Override
	public HttpResponseWS update(Coa coa) {
		int count =0;
		try {
			System.out.println(coa.toString());
			coa.normalizeRequest();
			count = coaDao.update(coa);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return EcountUtil.generateWSResponse(count,"Update");
	}

	@Override
	public HttpResponseWS delete(int id){
		int count = 0;
		try {
			count = coaDao.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return EcountUtil.generateWSResponse(count,"Delete");
	}

}
