package com.sanisoft.ecount.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanisoft.ecount.constant.EcountConstant;
import com.sanisoft.ecount.dao.CoaBalanceDao;
import com.sanisoft.ecount.entity.CoaBalance;
import com.sanisoft.ecount.ws.service.CoaBalanceService;

public class CoaBalanceServiceImpl implements CoaBalanceService {
	@Autowired
	CoaBalanceDao coaBalanceDao;
	
	@Override
	public CoaBalance get(int id) {
		return coaBalanceDao.get(id);
	}

	@Override
	public int update(CoaBalance coaBalance) {
		coaBalanceDao.update(coaBalance);
		return EcountConstant.SUCCESS;
	}

}
