package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventory_balance")
public class InventoryBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="inventory_id", nullable = false)
	public Inventory inv;
	@Column(name="qty")
	public int qty;
	@Column(name="balance")
	public BigDecimal balance;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cost_center_id")
	public CostCenter costCenter;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profit_center_id")
	public ProfitCenter profitCenter;
	@Column(name="created_by")	
	public String createdBy;
	@Column(name="created_dt")	
	public Date createdDate;
	@Column(name="modified_by")
	public String modifiedBy;
	@Column(name="modified_dt")
	public Date modifiedDate;
	@Column(name="min_qty")
	private int minQty;
	

	public void normalizeRequest() {
		setCreatedBy("Rizky");
		setCreatedDate(new Date());
		//riztemp
		CostCenter cc = new CostCenter();
		cc.setCreatedBy("Rizky");
		cc.setCreatedDate(new Date());
		cc.setId(1);
		setCostCenter(cc);
		ProfitCenter pc = new ProfitCenter();
		pc.setCreatedBy("Rizky");
		pc.setCreatedDate(new Date());
		pc.setId(1);
		setProfitCenter(pc);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Inventory getInv() {
		return inv;
	}
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public CostCenter getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}
	public ProfitCenter getProfitCenter() {
		return profitCenter;
	}
	public void setProfitCenter(ProfitCenter profitCenter) {
		this.profitCenter = profitCenter;
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
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	@Override
	public String toString() {
		return "InventoryBalance [id=" + id + ", inv=" + inv + ", qty=" + qty + ", balance=" + balance + ", costCenter="
				+ costCenter + ", profitCenter=" + profitCenter + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", minQty=" + minQty
				+ "]";
	}
		
}
