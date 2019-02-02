package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sanisoft.ecount.model.SelectItem;

@Entity
@Table(name="po")
public class PurchaseOrder {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="po_code")
	private String poCode;
	@Column(name="txn_date")
	private Timestamp trxnDate;
	@Column(name="est_delivery_date")
	private Timestamp estDeliveryDate;
	@Column (name="total_amount")
	private BigDecimal totalAmount;
	@Column(name="total_tax_amount")
	private BigDecimal totalTaxAmount;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supp_id")
	private CustomerSupplier custSupp;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stage_id")
	private Stage stage;
	@Column(name="po_type")
	private String poType;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;	
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
	@OneToMany(mappedBy="po",fetch=FetchType.LAZY)
	private Set<PurchaseOrderDetail> pods;
	@Transient
	private int nDraftO;
	@Transient
	private int nAwApprovalPO;
	@Transient
	private int nApprovedPO;
	@Transient
	private int nBilledPO;
	@Transient
	private BigDecimal totalAmtDraftPO;
	@Transient
	private BigDecimal totalAmtAwApprovalPO;
	@Transient
	private BigDecimal totalAmtApprovedPO;
	@Transient
	private BigDecimal totalAmtBilledPO;
	@Transient
	private int suppId;
	@Transient
	private String suppName;
	@Transient
	private String referrence;
	@Transient
	private List<Integer> poIds;
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
		setCreatedBy("Bergie");
		setCreatedDate(new Date());	
		for(PurchaseOrderDetail pod :getPods()) {
			pod.normalizeRequest();
		}
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoCode() {
		return poCode;
	}
	public void setPoCode(String poCode) {
		this.poCode = poCode;
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
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public String getPoType() {
		return poType;
	}
	public void setPoType(String poType) {
		this.poType = poType;
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
	public int getnPaid() {
		return nPaid;
	}
	public void setnPaid(int nPaid) {
		this.nPaid = nPaid;
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
	public Set<PurchaseOrderDetail> getPods() {
		return pods;
	}
	public void setPods(Set<PurchaseOrderDetail> pods) {
		this.pods = pods;
	}
	public int getnDraftO() {
		return nDraftO;
	}
	public void setnDraftO(int nDraftO) {
		this.nDraftO = nDraftO;
	}
	public int getnAwApprovalPO() {
		return nAwApprovalPO;
	}
	public void setnAwApprovalPO(int nAwApprovalPO) {
		this.nAwApprovalPO = nAwApprovalPO;
	}
	public int getnApprovedPO() {
		return nApprovedPO;
	}
	public void setnApprovedPO(int nApprovedPO) {
		this.nApprovedPO = nApprovedPO;
	}
	public int getnBilledPO() {
		return nBilledPO;
	}
	public void setnBilledPO(int nBilledPO) {
		this.nBilledPO = nBilledPO;
	}
	public BigDecimal getTotalAmtDraftPO() {
		return totalAmtDraftPO;
	}
	public void setTotalAmtDraftPO(BigDecimal totalAmtDraftPO) {
		this.totalAmtDraftPO = totalAmtDraftPO;
	}
	public BigDecimal getTotalAmtAwApprovalPO() {
		return totalAmtAwApprovalPO;
	}
	public void setTotalAmtAwApprovalPO(BigDecimal totalAmtAwApprovalPO) {
		this.totalAmtAwApprovalPO = totalAmtAwApprovalPO;
	}
	public BigDecimal getTotalAmtApprovedPO() {
		return totalAmtApprovedPO;
	}
	public void setTotalAmtApprovedPO(BigDecimal totalAmtApprovedPO) {
		this.totalAmtApprovedPO = totalAmtApprovedPO;
	}
	public BigDecimal getTotalAmtBilledPO() {
		return totalAmtBilledPO;
	}
	public void setTotalAmtBilledPO(BigDecimal totalAmtBilledPO) {
		this.totalAmtBilledPO = totalAmtBilledPO;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getReferrence() {
		return referrence;
	}
	public void setReferrence(String referrence) {
		this.referrence = referrence;
	}
	public List<Integer> getPoIds() {
		return poIds;
	}
	public void setPoIds(List<Integer> poIds) {
		this.poIds = poIds;
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
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", soCode=" + poCode + ", trxnDate=" + trxnDate + ", estDeliveryDate="
				+ estDeliveryDate + ", totalAmount=" + totalAmount + ", totalTaxAmount=" + totalTaxAmount
				+ ", custSupp=" + custSupp + ", stage=" + stage + ", po_type=" + poType + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", nDraft=" + nDraft + ", nAwApproval=" + nAwApproval + ", nAwPayment=" + nAwPayment
				+ ", nOverdue=" + nOverdue + ", pods=" + pods + ", suppId=" + suppId + ", suppName=" + suppName
				+ ", referrence=" + referrence + "]";
	}
	
}
