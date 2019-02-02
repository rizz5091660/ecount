package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import com.sanisoft.ecount.model.SelectItem;

@Entity
@Table(name="inventory")
public class Inventory {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")	
	private String name;
	@Column(name="code")	
	private String code;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inv_coa_id")
	private Coa coaInv;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="s_coa_id")
	private Coa coaSales;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="s_tax_id")
	private Tax taxSales;
	@Column(name="s_unit_price")
	private BigDecimal unitPriceSales;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="p_coa_id")
	private Coa coaPurchase;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="p_tax_id")
	private Tax taxPurchase;
	@Column(name="p_unit_price")
	private BigDecimal unitPricePurchase;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	@OneToOne(mappedBy = "inv", cascade = CascadeType.ALL)
	private InventoryBalance invBalance;	
	@Transient
	private int coaSalesId;
	@Transient
	private int taxSalesId;
	@Transient
	private int coaPurchaseId;
	@Transient
	private int taxPurchaseId;
	@Transient
	private List<Inventory> inventories = new ArrayList<Inventory>();

	@Transient
	List<SelectItem> taxes = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> coas = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> costCenters = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> profitCenters = new ArrayList<SelectItem>();
	@Column(name="is_sale")
	private boolean salesFlag;
	@Column(name="is_purchase")
	private boolean purchaseFlag;
	@Column(name="type")
	private String type;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Coa getCoaSales() {
		return coaSales;
	}
	public void setCoaSales(Coa coaSales) {
		this.coaSales = coaSales;
	}
	public Tax getTaxSales() {
		return taxSales;
	}
	public void setTaxSales(Tax taxSales) {
		this.taxSales = taxSales;
	}
	public BigDecimal getUnitPriceSales() {
		return unitPriceSales;
	}
	public void setUnitPriceSales(BigDecimal unitPriceSales) {
		this.unitPriceSales = unitPriceSales;
	}
	public Coa getCoaPurchase() {
		return coaPurchase;
	}
	public void setCoaPurchase(Coa coaPurchase) {
		this.coaPurchase = coaPurchase;
	}
	public Tax getTaxPurchase() {
		return taxPurchase;
	}
	public void setTaxPurchase(Tax taxPurchase) {
		this.taxPurchase = taxPurchase;
	}
	public BigDecimal getUnitPricePurchase() {
		return unitPricePurchase;
	}
	public void setUnitPricePurchase(BigDecimal unitPricePurchase) {
		this.unitPricePurchase = unitPricePurchase;
	}
	public int getCoaSalesId() {
		return coaSalesId;
	}
	public void setCoaSalesId(int coaSalesId) {
		this.coaSalesId = coaSalesId;
	}
	public int getTaxSalesId() {
		return taxSalesId;
	}
	public void setTaxSalesId(int taxSalesId) {
		this.taxSalesId = taxSalesId;
	}
	public int getCoaPurchaseId() {
		return coaPurchaseId;
	}
	public void setCoaPurchaseId(int coaPurchaseId) {
		this.coaPurchaseId = coaPurchaseId;
	}
	public int getTaxPurchaseId() {
		return taxPurchaseId;
	}
	public void setTaxPurchaseId(int taxPurchaseId) {
		this.taxPurchaseId = taxPurchaseId;
	}
	public List<Inventory> getInventories() {
		return inventories;
	}
	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
	public InventoryBalance getInvBalance() {
		return invBalance;
	}
	public void setInvBalance(InventoryBalance invBalance) {
		this.invBalance = invBalance;
	}
	public List<SelectItem> getTaxes() {
		return taxes;
	}
	public void setTaxes(List<SelectItem> taxes) {
		this.taxes = taxes;
	}
	public List<SelectItem> getCoas() {
		return coas;
	}
	public void setCoas(List<SelectItem> coas) {
		this.coas = coas;
	}
	public List<SelectItem> getCostCenters() {
		return costCenters;
	}
	public void setCostCenters(List<SelectItem> costCenters) {
		this.costCenters = costCenters;
	}
	public List<SelectItem> getProfitCenters() {
		return profitCenters;
	}
	public void setProfitCenters(List<SelectItem> profitCenters) {
		this.profitCenters = profitCenters;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSalesFlag() {
		return salesFlag;
	}
	public void setSalesFlag(boolean salesFlag) {
		this.salesFlag = salesFlag;
	}
	public boolean isPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(boolean purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public Coa getCoaInv() {
		return coaInv;
	}
	public void setCoaInv(Coa coaInv) {
		this.coaInv = coaInv;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", code=" + code + ", coaInv=" + coaInv + ", coaSales="
				+ coaSales + ", taxSales=" + taxSales + ", unitPriceSales=" + unitPriceSales + ", coaPurchase="
				+ coaPurchase + ", taxPurchase=" + taxPurchase + ", unitPricePurchase=" + unitPricePurchase
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", coaSalesId=" + coaSalesId + ", taxSalesId=" + taxSalesId
				+ ", coaPurchaseId=" + coaPurchaseId + ", taxPurchaseId=" + taxPurchaseId + ", inventories="
				+ inventories + ", invBalance=" + invBalance + ", taxes=" + taxes + ", coas=" + coas + ", costCenters="
				+ costCenters + ", profitCenters=" + profitCenters + ", salesFlag=" + salesFlag + ", purchaseFlag="
				+ purchaseFlag + ", type=" + type + "]";
	}

}
