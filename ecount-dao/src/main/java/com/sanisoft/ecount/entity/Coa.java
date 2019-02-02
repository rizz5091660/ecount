package com.sanisoft.ecount.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name="coa_acc")
public class Coa{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_dt") 
	private Date createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="modified_dt")
	private Date modifiedDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="acc_type_id")
	private AccountType accountType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="acc_det_type_id")
	private AccountDetailType accountDetailType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tax_id")
	private Tax taxObj; 
	@OneToMany(fetch=FetchType.LAZY,mappedBy="coa")
	private Set<SalesOrderDetail> sods = new HashSet<SalesOrderDetail>();	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="coa",cascade = CascadeType.ALL)
	private Set<CoaBalance> coaBalances = new HashSet<CoaBalance>();	
	@Transient
	List<SelectItem> accountTypes = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> accountDetailTypes = new ArrayList<SelectItem>();
	@Transient
	List<SelectItem> taxes = new ArrayList<SelectItem>();
	@Transient
	List<Coa>coas = new ArrayList<Coa>();
	@Transient
	BigDecimal balance;
	@Transient
	Date asOf;
	@Transient
	CoaBalance coaBalance;
	
	
	public void normalizeRequest() {
		setCreatedBy("Rizky");
		setCreatedDate(new Date());
	}
	
	public Coa() {
		
	}
	
	public Coa(int id) {
		super();
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
	public Tax getTaxObj() {
		return taxObj;
	}
	public void setTaxObj(Tax taxObj) {
		this.taxObj = taxObj;
	}
	
	public List<SelectItem> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<SelectItem> accountTypes) {
		this.accountTypes = accountTypes;
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
	public Set<SalesOrderDetail> getSods() {
		return sods;
	}
	public void setSods(Set<SalesOrderDetail> sods) {
		this.sods = sods;
	}
	public List<Coa> getCoas() {
		return coas;
	}
	public void setCoas(List<Coa> coas) {
		this.coas = coas;
	}

	public AccountDetailType getAccountDetailType() {
		return accountDetailType;
	}

	public void setAccountDetailType(AccountDetailType accountDetailType) {
		this.accountDetailType = accountDetailType;
	}

	public List<SelectItem> getAccountDetailTypes() {
		return accountDetailTypes;
	}

	public void setAccountDetailTypes(List<SelectItem> accountDetailTypes) {
		this.accountDetailTypes = accountDetailTypes;
	}


	public Set<CoaBalance> getCoaBalances() {
		if(coaBalances==null) {
			coaBalances = new HashSet<CoaBalance>();
		}
		return coaBalances;
	}

	public void setCoaBalances(Set<CoaBalance> coaBalances) {
		this.coaBalances = coaBalances;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getAsOf() {
		return asOf;
	}

	public void setAsOf(Date asOf) {
		this.asOf = asOf;
	}
		
	public CoaBalance getCoaBalance() {
		if(coaBalances!=null && coaBalances.size()>0) {
			List<CoaBalance> list = new ArrayList<>(coaBalances);
			coaBalance = list.get(0);
		}
		return coaBalance;
	}


	@Override
	public String toString() {
		return "Coa [id=" + id + ", name=" + name + ", description=" + description + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", accountType=" + accountType + ", accountDetailType=" + accountDetailType + ", taxObj=" + taxObj
				+ ", sods=" + sods + ", accountTypes=" + accountTypes + ", accountDetailTypes=" + accountDetailTypes
				+ ", taxes=" + taxes + ", coas=" + coas + "]";
	}


	
}
