package com.sanisoft.ecount.entity;

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

import com.sanisoft.ecount.model.SelectItem;
import com.sanisoft.ecount.model.SelectItemGroup;

/*
@Entity
@Table(name="coa")
*/
public class CoaOld{
	/*
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	@Column(name="name")
	private String name;
	@Column(name="coa_code")
	private String coaCd;
	@Column(name="favorite")
	private String favorite;
	@Column(name="description")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="l1")
	private AccountType accountType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="l2")
	private Branch branch;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="l3")
	private CustomerSupplier custSupp;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="l4")
	private Division division;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="l5")
	private CoaCustomLevelN1 customLevel; 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tax_id")
	private Tax taxObj; 
	@Transient
	private int l1AccountType;
	@Transient
	private int l2Branch;
	@Transient
	private int l3CustSupp;
	@Transient
	private int l4Division;
	@Transient
	private int l5Custom;	
	@Transient
	private int tax;
	@Transient
	private String l1AccountTypName;
	@Transient
	private String l1AccountTypGrp;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="coa")
	private Set<SalesOrderDetail> sods = new HashSet<SalesOrderDetail>();	
	@Transient
	List<SelectItem> accountTypes = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> branches = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> custSupps = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> divisions = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> customFields1 = new ArrayList<SelectItem>(); 
	@Transient
	List<SelectItem> taxes = new ArrayList<SelectItem>();
	@Transient
	private String taxName;
	@Transient
	List<CoaOld>coas = new ArrayList<CoaOld>();
	
	public void normalizeRequest() {
		accountType = new AccountType();
		accountType.setId(l1AccountType);
		branch = new Branch();
		branch.setId(l2Branch);
		custSupp = new CustomerSupplier();
		custSupp.setId(l3CustSupp);
		division = new Division();
		division.setId(l4Division);
		customLevel = new CoaCustomLevelN1();
		customLevel.setId(1);
		taxObj = new Tax();
		taxObj.setId(tax);
		setCreatedBy("Rizky");
		setCreatedDate(new Date());
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public CustomerSupplier getCustSupp() {
		return custSupp;
	}
	public void setCustSupp(CustomerSupplier custSupp) {
		this.custSupp = custSupp;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public CoaCustomLevelN1 getCustomLevel() {
		return customLevel;
	}
	public void setCustomLevel(CoaCustomLevelN1 customLevel) {
		this.customLevel = customLevel;
	}
	public Tax getTaxObj() {
		return taxObj;
	}
	public void setTaxObj(Tax taxObj) {
		this.taxObj = taxObj;
	}
	public int getL1AccountType() {
		return l1AccountType;
	}
	public void setL1AccountType(int l1AccountType) {
		this.l1AccountType = l1AccountType;
	}
	public int getL2Branch() {
		return l2Branch;
	}
	public void setL2Branch(int l2Branch) {
		this.l2Branch = l2Branch;
	}
	public int getL3CustSupp() {
		return l3CustSupp;
	}
	public void setL3CustSupp(int l3CustSupp) {
		this.l3CustSupp = l3CustSupp;
	}
	public int getL4Division() {
		return l4Division;
	}
	public void setL4Division(int l4Division) {
		this.l4Division = l4Division;
	}
	public int getL5Custom() {
		return l5Custom;
	}
	public void setL5Custom(int l5Custom) {
		this.l5Custom = l5Custom;
	}
	public String getCoaCd() {
		return coaCd;
	}
	public void setCoaCd(String coaCd) {
		this.coaCd = coaCd;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getL1AccountTypName() {
		return l1AccountTypName;
	}
	public void setL1AccountTypName(String l1AccountTypName) {
		this.l1AccountTypName = l1AccountTypName;
	}
	public String getL1AccountTypGrp() {
		return l1AccountTypGrp;
	}
	public void setL1AccountTypGrp(String l1AccountTypGrp) {
		this.l1AccountTypGrp = l1AccountTypGrp;
	}
	public List<SelectItem> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<SelectItem> accountTypes) {
		this.accountTypes = accountTypes;
	}

	public List<SelectItem> getBranches() {
		return branches;
	}

	public void setBranches(List<SelectItem> branches) {
		this.branches = branches;
	}

	public List<SelectItem> getCustSupps() {
		return custSupps;
	}

	public void setCustSupps(List<SelectItem> custSupps) {
		this.custSupps = custSupps;
	}

	public List<SelectItem> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<SelectItem> divisions) {
		this.divisions = divisions;
	}

	public List<SelectItem> getCustomFields1() {
		return customFields1;
	}

	public void setCustomFields1(List<SelectItem> customFields1) {
		this.customFields1 = customFields1;
	}

	public List<SelectItem> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<SelectItem> taxes) {
		this.taxes = taxes;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}	
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public Set<SalesOrderDetail> getSods() {
		return sods;
	}
	public void setSods(Set<SalesOrderDetail> sods) {
		this.sods = sods;
	}
	public List<CoaOld> getCoas() {
		return coas;
	}
	public void setCoas(List<CoaOld> coas) {
		this.coas = coas;
	}
	@Override
	public String toString() {
		return "Coa [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", name=" + name + ", coaCd=" + coaCd + ", favorite="
				+ favorite + ", description=" + description + ", accountType=" + accountType + ", branch=" + branch
				+ ", custSupp=" + custSupp + ", division=" + division + ", customLevel=" + customLevel + ", taxObj="
				+ taxObj + ", l1AccountType=" + l1AccountType + ", l2Branch=" + l2Branch + ", l3CustSupp=" + l3CustSupp
				+ ", l4Division=" + l4Division + ", l5Custom=" + l5Custom + ", tax=" + tax + ", l1AccountTypName="
				+ l1AccountTypName + ", l1AccountTypGrp=" + l1AccountTypGrp + ", accountTypes=" + accountTypes
				+ ", branches=" + branches + ", custSupps=" + custSupps + ", divisions=" + divisions
				+ ", customFields1=" + customFields1 + ", taxes=" + taxes + ", taxName=" + taxName + "]";
	}
	*/
	
}
