package com.sanisoft.ecount.dao;

import com.sanisoft.ecount.entity.CoaBalance;

public interface CoaBalanceDao {
	public int create(CoaBalance coaBalance);
	public CoaBalance get(int id);
	public CoaBalance getByCoaIdMontYear(int coaId,int month, int year);
	public int update(CoaBalance coaBalance);

}
