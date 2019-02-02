package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coa_balance")
public class CoaBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="coa_id")
	Coa coa;
	@Column(name="month")
	private int month;
	@Column(name="year")
	private int year;
	@Column(name="balance")
	private BigDecimal balance;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	@Column(name="as_of") 
	private Date asOf;
	
	public void normalizeRequest() {
		setCreatedBy("Rizky");
		setCreatedDate(new Date());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Coa getCoa() {
		return coa;
	}
	public void setCoa(Coa coa) {
		this.coa = coa;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	public Date getAsOf() {
		return asOf;
	}
	public void setAsOf(Date asOf) {
		this.asOf = asOf;
	}
	
	
}
