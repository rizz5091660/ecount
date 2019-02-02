package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="txn_detail")
public class TransactionDetail {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@ManyToOne
    @JoinColumn(name="txn_id", nullable=false)
	private Transaction txn;
	@ManyToOne
	@JoinColumn(name="coa_id",nullable=false)
	private Coa coa;	
	@Column(name="sequence")
	private int sequence;
	@Column(name="db_amount")
	private BigDecimal dbAmount;
	@Column(name="cr_amount")
	private BigDecimal crAmount;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt")
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	
	public TransactionDetail() {
		
	}
	
	public TransactionDetail(Transaction txn, Coa coa, int sequence, BigDecimal dbAmount, BigDecimal crAmount, String createdBy, Date createdDate) {
		super();
		this.txn = txn;
		this.coa = coa;
		this.sequence = sequence;
		this.dbAmount = dbAmount;
		this.crAmount = crAmount;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Transaction getTxn() {
		return txn;
	}
	public void setTxn(Transaction txn) {
		this.txn = txn;
	}
	public Coa getCoa() {
		return coa;
	}
	public void setCoa(Coa coa) {
		this.coa = coa;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public BigDecimal getDbAmount() {
		return dbAmount;
	}
	public void setDbAmount(BigDecimal dbAmount) {
		this.dbAmount = dbAmount;
	}
	public BigDecimal getCrAmount() {
		return crAmount;
	}
	public void setCrAmount(BigDecimal crAmount) {
		this.crAmount = crAmount;
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
