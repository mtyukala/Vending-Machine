package com.vending.machine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Table(name = "coin")
@Entity
public class Coin extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "coin_genderator")
	@SequenceGenerator(name = "coin_generator", sequenceName = "coin_sequence", initialValue = 100)
	private Long id;

	@Min(message = "Value cannot be negative", value = 0)
	private double amount;
	@Size(min = 2, max = 10, message = "Description must be between {min} and {max} characters")
	private String description;

	public Coin() {
		this.amount = 0;
		this.description = "";
	}

	public Coin(@Min(message = "Value cannot be negative", value = 0) double amount,
			@Size(min = 2, max = 10, message = "Description must be between {min} and {max} characters") String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
