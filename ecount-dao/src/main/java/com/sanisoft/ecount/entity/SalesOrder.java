package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sanisoft.ecount.model.SelectItem;

@Entity
@Table(name="so")
public class SalesOrder {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="so_code")
	private String soCode;
	@Column(name="txn_date")
	private Timestamp trxnDate;
	@Column(name="est_delivery_date")
	private Timestamp estDeliveryDate;
	@Column (name="total_amount")
	private BigDecimal totalAmount;
	@Column(name="total_tax_amount")
	private BigDecimal totalTaxAmount;
	@Transient
	private BigDecimal totalAmountNoTax;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cust_id")
	private CustomerSupplier custSupp;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stage_id")
	private Stage stage;
	@Column(name="shipping_cost")
	private BigDecimal shippingCost;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;	
	@Column(name="so_type")
	private String soType;
	@Column(name="referrence")
	private String referrence;	
	@Transient
	private BigDecimal totalAmountCOGS;	
	@Transient
	private int nDraft;
	@Transient
	private int nAwApproval;
	@Transient
	private int nAwPayment;
	@Transient
	private int nOverdue;
	@Transient
	private int nPaid;
	@Transient
	private BigDecimal totalAmtDraft;
	@Transient
	private BigDecimal totalAmtAwApproval;
	@Transient
	private BigDecimal totalAmtAwPayment;
	@Transient
	private BigDecimal totalAmtOverdue;
	@Transient
	private BigDecimal totalAmtPaid;	
	@Transient
	private int nDraftQ;
	@Transient
	private int nAwApprovalQ;
	@Transient
	private int nAwPaymentQ;
	@Transient
	private int nPaidQ;
	@Transient
	private int nOverdueQ;	
	@Transient
	private BigDecimal totalAmtDraftQ;
	@Transient
	private BigDecimal totalAmtAwApprovalQ;
	@Transient
	private BigDecimal totalAmtAwPaymentQ;
	@Transient
	private BigDecimal totalAmtOverdueQ;
	@Transient
	private BigDecimal totalAmtPaidQ;
	@OneToMany(mappedBy="so",fetch=FetchType.LAZY)
	private Set<SalesOrderDetail> sods;
	@Transient
	private int custId;
	@Transient
	private String custName;
	@Transient
	private List<Integer> salesIds;
	@Transient
	List<SelectItem> custSupps = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> inventories = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> taxes = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> coas = new ArrayList<SelectItem>();
	@Transient
	List<Inventory> inventories2 = new ArrayList<Inventory>();
	
	public void normalizeRequest() {;
		setCreatedBy("Rizky");
		setCreatedDate(new Date());	
		for(SalesOrderDetail sod :getSods()) {
			sod.normalizeRequest();
		}
		
	}
	
	public SalesOrder() {
		
	}

	public SalesOrder(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public int getnDraft() {
		return nDraft;
	}
	public void setnDraft(int nDraft) {
		this.nDraft = nDraft;
	}
	public int getnAwApproval() {
		return nAwApproval;
	}
	public void setnAwApproval(int nAwApproval) {
		this.nAwApproval = nAwApproval;
	}
	public int getnAwPayment() {
		return nAwPayment;
	}
	public void setnAwPayment(int nAwPayment) {
		this.nAwPayment = nAwPayment;
	}
	public int getnOverdue() {
		return nOverdue;
	}
	public void setnOverdue(int nOverdue) {
		this.nOverdue = nOverdue;
	}
	public Set<SalesOrderDetail> getSods() {
		if(sods==null) {
			sods = new HashSet<SalesOrderDetail>();
		}
		return sods;
	}
	public void setSods(Set<SalesOrderDetail> sods) {
		this.sods = sods;
	}
	public String getSoCode() {
		return soCode;
	}
	public void setSoCode(String soCode) {
		this.soCode = soCode;
	}
	public Timestamp getTrxnDate() {
		return trxnDate;
	}
	public void setTrxnDate(Timestamp trxnDate) {
		this.trxnDate = trxnDate;
	}
	public Timestamp getEstDeliveryDate() {
		return estDeliveryDate;
	}
	public void setEstDeliveryDate(Timestamp estDeliveryDate) {
		this.estDeliveryDate = estDeliveryDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public CustomerSupplier getCustSupp() {
		return custSupp;
	}
	public void setCustSupp(CustomerSupplier custSupp) {
		this.custSupp = custSupp;
	}
	public BigDecimal getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getReferrence() {
		return referrence;
	}
	public void setReferrence(String referrence) {
		this.referrence = referrence;
	}
	public List<Integer> getSalesIds() {
		return salesIds;
	}
	public void setSalesIds(List<Integer> salesIds) {
		this.salesIds = salesIds;
	}	
	public List<SelectItem> getCustSupps() {
		return custSupps;
	}
	public void setCustSupps(List<SelectItem> custSupps) {
		this.custSupps = custSupps;
	}
	public List<SelectItem> getInventories() {
		return inventories;
	}
	public void setInventories(List<SelectItem> inventories) {
		this.inventories = inventories;
	}	
	public String getSoType() {
		return soType;
	}
	public void setSoType(String soType) {
		this.soType = soType;
	}	
	public int getnDraftQ() {
		return nDraftQ;
	}
	public void setnDraftQ(int nDraftQ) {
		this.nDraftQ = nDraftQ;
	}
	public int getnAwApprovalQ() {
		return nAwApprovalQ;
	}
	public void setnAwApprovalQ(int nAwApprovalQ) {
		this.nAwApprovalQ = nAwApprovalQ;
	}
	public int getnAwPaymentQ() {
		return nAwPaymentQ;
	}
	public void setnAwPaymentQ(int nAwPaymentQ) {
		this.nAwPaymentQ = nAwPaymentQ;
	}
	public int getnOverdueQ() {
		return nOverdueQ;
	}
	public void setnOverdueQ(int nOverdueQ) {
		this.nOverdueQ = nOverdueQ;
	}
	public int getnPaid() {
		return nPaid;
	}
	public void setnPaid(int nPaid) {
		this.nPaid = nPaid;
	}
	public int getnPaidQ() {
		return nPaidQ;
	}
	public void setnPaidQ(int nPaidQ) {
		this.nPaidQ = nPaidQ;
	}	
	public BigDecimal getTotalAmtDraft() {
		return totalAmtDraft;
	}
	public void setTotalAmtDraft(BigDecimal totalAmtDraft) {
		this.totalAmtDraft = totalAmtDraft;
	}
	public BigDecimal getTotalAmtAwApproval() {
		return totalAmtAwApproval;
	}
	public void setTotalAmtAwApproval(BigDecimal totalAmtAwApproval) {
		this.totalAmtAwApproval = totalAmtAwApproval;
	}
	public BigDecimal getTotalAmtAwPayment() {
		return totalAmtAwPayment;
	}
	public void setTotalAmtAwPayment(BigDecimal totalAmtAwPayment) {
		this.totalAmtAwPayment = totalAmtAwPayment;
	}
	public BigDecimal getTotalAmtOverdue() {
		return totalAmtOverdue;
	}
	public void setTotalAmtOverdue(BigDecimal totalAmtOverdue) {
		this.totalAmtOverdue = totalAmtOverdue;
	}
	public BigDecimal getTotalAmtPaid() {
		return totalAmtPaid;
	}
	public void setTotalAmtPaid(BigDecimal totalAmtPaid) {
		this.totalAmtPaid = totalAmtPaid;
	}
	public BigDecimal getTotalAmtDraftQ() {
		return totalAmtDraftQ;
	}
	public void setTotalAmtDraftQ(BigDecimal totalAmtDraftQ) {
		this.totalAmtDraftQ = totalAmtDraftQ;
	}
	public BigDecimal getTotalAmtAwApprovalQ() {
		return totalAmtAwApprovalQ;
	}
	public void setTotalAmtAwApprovalQ(BigDecimal totalAmtAwApprovalQ) {
		this.totalAmtAwApprovalQ = totalAmtAwApprovalQ;
	}
	public BigDecimal getTotalAmtAwPaymentQ() {
		return totalAmtAwPaymentQ;
	}
	public void setTotalAmtAwPaymentQ(BigDecimal totalAmtAwPaymentQ) {
		this.totalAmtAwPaymentQ = totalAmtAwPaymentQ;
	}
	public BigDecimal getTotalAmtOverdueQ() {
		return totalAmtOverdueQ;
	}
	public void setTotalAmtOverdueQ(BigDecimal totalAmtOverdueQ) {
		this.totalAmtOverdueQ = totalAmtOverdueQ;
	}
	public BigDecimal getTotalAmtPaidQ() {
		return totalAmtPaidQ;
	}
	public void setTotalAmtPaidQ(BigDecimal totalAmtPaidQ) {
		this.totalAmtPaidQ = totalAmtPaidQ;
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
	public List<Inventory> getInventories2() {
		return inventories2;
	}
	public void setInventories2(List<Inventory> inventories2) {
		this.inventories2 = inventories2;
	}
	public BigDecimal getTotalAmountCOGS() {
		return totalAmountCOGS;
	}
	public void setTotalAmountCOGS(BigDecimal totalAmountCOGS) {
		this.totalAmountCOGS = totalAmountCOGS;
	}	
	public BigDecimal getTotalAmountNoTax() {
		return totalAmountNoTax;
	}
	public void setTotalAmountNoTax(BigDecimal totalAmountNoTax) {
		this.totalAmountNoTax = totalAmountNoTax;
	}
	@Override
	public String toString() {
		return "SalesOrder [id=" + id + ", soCode=" + soCode + ", trxnDate=" + trxnDate + ", estDeliveryDate="
				+ estDeliveryDate + ", totalAmount=" + totalAmount + ", totalTaxAmount=" + totalTaxAmount
				+ ", totalAmountNoTax=" + totalAmountNoTax + ", custSupp=" + custSupp + ", stage=" + stage
				+ ", shippingCost=" + shippingCost + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", soType=" + soType
				+ ", referrence=" + referrence + ", totalAmountCOGS=" + totalAmountCOGS + ", nDraft=" + nDraft
				+ ", nAwApproval=" + nAwApproval + ", nAwPayment=" + nAwPayment + ", nOverdue=" + nOverdue + ", nPaid="
				+ nPaid + ", totalAmtDraft=" + totalAmtDraft + ", totalAmtAwApproval=" + totalAmtAwApproval
				+ ", totalAmtAwPayment=" + totalAmtAwPayment + ", totalAmtOverdue=" + totalAmtOverdue
				+ ", totalAmtPaid=" + totalAmtPaid + ", nDraftQ=" + nDraftQ + ", nAwApprovalQ=" + nAwApprovalQ
				+ ", nAwPaymentQ=" + nAwPaymentQ + ", nPaidQ=" + nPaidQ + ", nOverdueQ=" + nOverdueQ
				+ ", totalAmtDraftQ=" + totalAmtDraftQ + ", totalAmtAwApprovalQ=" + totalAmtAwApprovalQ
				+ ", totalAmtAwPaymentQ=" + totalAmtAwPaymentQ + ", totalAmtOverdueQ=" + totalAmtOverdueQ
				+ ", totalAmtPaidQ=" + totalAmtPaidQ + ", sods=" + sods + ", custId=" + custId + ", custName="
				+ custName + ", salesIds=" + salesIds + ", custSupps=" + custSupps + ", inventories=" + inventories
				+ ", taxes=" + taxes + ", coas=" + coas + ", inventories2=" + inventories2 + "]";
	}

}
