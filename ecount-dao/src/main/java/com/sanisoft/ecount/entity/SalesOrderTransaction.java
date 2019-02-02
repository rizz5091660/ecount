package com.sanisoft.ecount.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="so_txn")
public class SalesOrderTransaction {
	@Id
	@GeneratedValue
	@Column
	private int id;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="so_id")
	SalesOrder so;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="txn_id")
	Transaction txn;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	
	public SalesOrderTransaction() {
		
	}
	
	public SalesOrderTransaction(int soId, int txnId, String createdBy, Date createdDate ) {
		this.so = new SalesOrder(soId);
		this.txn = new Transaction(txnId);
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SalesOrder getSo() {
		return so;
	}

	public void setSo(SalesOrder so) {
		this.so = so;
	}

	public Transaction getTxn() {
		return txn;
	}
	public void setTxn(Transaction txn) {
		this.txn = txn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
