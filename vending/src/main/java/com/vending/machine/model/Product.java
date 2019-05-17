package com.vending.machine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
	@NotBlank
	@Size(min = 5, max = 50)
	private String name;
	private float price;
	private float weight;

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

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
