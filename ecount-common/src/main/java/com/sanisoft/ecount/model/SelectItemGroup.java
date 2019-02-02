package com.sanisoft.ecount.model;

import java.util.List;

public class SelectItemGroup {
	private String label;
	private String value;
	private List<SelectItem> items;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<SelectItem> getItems() {
		return items;
	}
	public void setItems(List<SelectItem> items) {
		this.items = items;
	}
	
	

}
