package com.sanisoft.ecount.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
@Entity
@Table(name="division")
@Proxy(lazy = false)
*/
public class Division{
	/*
	@Id
	@GeneratedValue
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
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="division")
	private Set<Coa> coas = new HashSet<Coa>();	
	
	
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
	public Set<Coa> getCoas() {
		return coas;
	}
	public void setCoas(Set<Coa> coas) {
		this.coas = coas;
	}
	*/
}
