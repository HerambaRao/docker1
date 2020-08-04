package com.product.catalog.ents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Product")
public class Product {
@Id
@Column(name ="Id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "Category_Id")
private Long categoryId;
@Column(name = "Name")
private String name;
@Column(name = "Description")
private String description;





public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCategoryId() {
	return categoryId;
}
public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}




@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	if (categoryId == null) {
		if (other.categoryId != null)
			return false;
	} else if (!categoryId.equals(other.categoryId))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}







}
