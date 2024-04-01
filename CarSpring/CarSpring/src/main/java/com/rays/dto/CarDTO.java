package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.rays.common.DropDownList;

@Entity
@Table(name = "ST_CAR")
public class CarDTO implements DropDownList {

	@Id
	@GeneratedValue(generator = "rays")
	@GenericGenerator(name = "rays", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)

	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OWNERNAME", length = 50)
	public String ownerName;

	@Column(name = "CARNAME", length = 50)
	public String carName;

	@Column(name = "MODEL", length = 50)
	public String model;

	@Column(name = "PURCHASEDATE", length = 50)
	public Date purchaseDate;

	@Column(name = "PRICE", length = 50)
	public Long price;

	@Column(name = "IMAGEID", length = 50)
	public Long imageId;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	
	public String getKey() {

		return id + "";
	}
	
	public String getValue() {
		return purchaseDate +"";
	}

	

}
