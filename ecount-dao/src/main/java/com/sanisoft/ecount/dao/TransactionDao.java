package com.sanisoft.ecount.dao;

import com.sanisoft.ecount.entity.Transaction;
import com.sanisoft.ecount.entity.TransactionDetail;

public interface TransactionDao {
	public int create(Transaction trxn);
	public Transaction get (int id);
	public int update(Transaction trxn);
	public int createDetail(TransactionDetail trxnDtl);

}
