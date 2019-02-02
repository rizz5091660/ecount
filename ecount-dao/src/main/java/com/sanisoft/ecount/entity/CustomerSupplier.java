package com.sanisoft.ecount.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sanisoft.ecount.model.SelectItem;

@Entity
@Table(name="cust_supp")
public class CustomerSupplier{
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
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
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	@Column(name="type")
	private String type;
	/*
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="custSupp")
	private Set<Coa> coas = new HashSet<Coa>();	*/
	
	@Transient
	List<SelectItem> countTypes = new ArrayList<SelectItem>();
	@Transient
	List<CustomerSupplier> custSupps = new ArrayList<CustomerSupplier>();
	
	
	public void normalizeRequest() {
		setCreatedBy("Rizky");
		setCreatedDate(new Date());
		if(getAddress()==null) {
			address = new Address();
		}
		address.setCreatedBy("Rizky");
		address.setCreatedDate(new Date());
	}
	
	public CustomerSupplier() {
		
	}
	
	public CustomerSupplier (int id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<SelectItem> getCountTypes() {
		return countTypes;
	}
	public void setCountTypes(List<SelectItem> countTypes) {
		this.countTypes = countTypes;
	}
	public List<CustomerSupplier> getCustSupps() {
		return custSupps;
	}
	public void setCustSupps(List<CustomerSupplier> custSupps) {
		this.custSupps = custSupps;
	}	
	
}
