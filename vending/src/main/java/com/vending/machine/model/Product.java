package com.vending.machine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class to represent a product in the vending machine
 * 
 * @author Mkhululi Tyukala
 *
 */
@Table(name = "product")
@Entity
public class Product extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "product_genderator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", initialValue = 100)
	private Long id;
	@NotNull(message = "Please, enter a description of the product")
	@Size(min = 5, max = 50, message = "Description must be between {min} and {max} characters")
	private String name;
	@Min(message = "Price cannot be negative", value = 0)
	private float price;
	private float weight;
	private String pictureURL;

	public Product() {

	}

	public Product(
			@NotNull(message = "Please, enter a description of the product") @Size(min = 5, max = 50, message = "Description must be between {min} and {max} characters") String name,
			@Min(message = "Price cannot be negative", value = 0) float price, float weight, String pictureURL) {
		super();
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.pictureURL = pictureURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public float getWeight() {
		return weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product p = (Product) obj;
		return (p.getPrice() == getPrice() && p.getName().equals(getName()) && p.getPictureURL().equals(getPictureURL())
				&& p.getWeight() == getWeight());

		// return true;
	}

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 1;

		result = prime * result + (getId().hashCode());
		result = prime * result + Long.valueOf(Double.doubleToLongBits(getPrice())).hashCode();
		result = prime * result + getName().hashCode();
		result = prime * result + Long.valueOf(Double.doubleToLongBits(getWeight())).hashCode();
		result = prime * result + getPictureURL().hashCode();

		return result;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURLString) {
		this.pictureURL = pictureURLString;
	}

}
