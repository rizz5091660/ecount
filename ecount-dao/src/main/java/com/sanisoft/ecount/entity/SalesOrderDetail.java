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
import javax.persistence.Transient;

import com.sanisoft.ecount.model.SelectItem;

@Entity
@Table(name="so_detail")
public class SalesOrderDetail {
	@Id
	@GeneratedValue
	@Column (name="id")
	private int id;
	@ManyToOne
    @JoinColumn(name="so_id", nullable=false)
	private SalesOrder so;
	@ManyToOne
	@JoinColumn(name="inventory_id",nullable=false)
	private Inventory inventory;
	@Column(name="price")
	private BigDecimal unitPrice;
	@Column (name="qty")
	private int quantity;
	@Column (name="txn_amount")
	private BigDecimal txnAmount;
	@Column (name="tax_amount")
	private BigDecimal taxAmount;
	@Transient
	private BigDecimal txnAmountNoTax;
	@Transient
	private BigDecimal txnCogs;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	@ManyToOne
	@JoinColumn(name="coa_id", nullable=false)
	private Coa coa;	
	@ManyToOne
	@JoinColumn(name="tax_id", nullable=false)
	private Tax tax;	
	@Transient
	private SelectItem invDD;
	@Transient
	private SelectItem coaDD;
	@Transient
	private SelectItem taxDD;
		
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

	public SalesOrder getSo() {
		return so;
	}
	public void setSo(SalesOrder so) {
		this.so = so;
	}
	public BigDecimal getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
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
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Coa getCoa() {
		return coa;
	}
	public void setCoa(Coa coa) {
		this.coa = coa;
	}
	public SelectItem getInvDD() {
		return invDD;
	}
	public void setInvDD(SelectItem invDD) {
		this.invDD = invDD;
	}
	public SelectItem getCoaDD() {
		return coaDD;
	}
	public void setCoaDD(SelectItem coaDD) {
		this.coaDD = coaDD;
	}
	public SelectItem getTaxDD() {
		return taxDD;
	}
	public void setTaxDD(SelectItem taxDD) {
		this.taxDD = taxDD;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public BigDecimal getTxnAmountNoTax() {
		return txnAmountNoTax;
	}

	public void setTxnAmountNoTax(BigDecimal txnAmountNoTax) {
		this.txnAmountNoTax = txnAmountNoTax;
	}

	public BigDecimal getTxnCogs() {
		return txnCogs;
	}

	public void setTxnCogs(BigDecimal txnCogs) {
		this.txnCogs = txnCogs;
	}

	@Override
	public String toString() {
		return "SalesOrderDetail [id=" + id + ", so=" + so + ", inventory=" + inventory + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + ", txnAmount=" + txnAmount + ", taxAmount=" + taxAmount
				+ ", txnAmountNoTax=" + txnAmountNoTax + ", txnCogs=" + txnCogs + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", coa=" + coa + ", tax=" + tax + ", invDD=" + invDD + ", coaDD=" + coaDD + ", taxDD=" + taxDD + "]";
	}
	
	

	
}
