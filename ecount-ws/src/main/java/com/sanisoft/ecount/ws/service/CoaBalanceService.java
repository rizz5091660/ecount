package com.sanisoft.ecount.ws.service;

import com.sanisoft.ecount.entity.CoaBalance;

public interface CoaBalanceService {
	public CoaBalance get(int id);
	public int update(CoaBalance coaBalance);
}
