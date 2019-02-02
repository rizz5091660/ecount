package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="txn")
public class Transaction {	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="reff_code")
	private String reffCode;
	@Column(name="remark")
	private String remark;
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	@Column(name="txn_date")
	private Date transactionDate;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt")
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	//riztemp
	@Column(name="repayment_id")
	private int repaymentId;
	@OneToMany(mappedBy="txn",fetch=FetchType.LAZY)
	private Set<TransactionDetail> txnDetails;
	
	public Transaction() {
		
	}
	
	public Transaction(int id) {
		this.id = id;
	}
	
	public Transaction(String reffCode, String remark, BigDecimal totalAmount, Date transactionDate, String createdBy, Date createdDate ) {
		super();
		this.reffCode = reffCode;
		this.remark = remark;
		this.totalAmount = totalAmount;
		this.transactionDate = transactionDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReffCode() {
		return reffCode;
	}
	public void setReffCode(String reffCode) {
		this.reffCode = reffCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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
	public int getRepaymentId() {
		return repaymentId;
	}
	public void setRepaymentId(int repaymentId) {
		this.repaymentId = repaymentId;
	}
	public Set<TransactionDetail> getTxnDetails() {
		return txnDetails;
	}
	public void setTxnDetails(Set<TransactionDetail> txnDetails) {
		this.txnDetails = txnDetails;
	}

}
