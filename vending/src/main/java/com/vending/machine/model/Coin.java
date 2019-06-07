package com.vending.machine.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "coin")
@Entity
public class Coin extends AuditModel implements Comparable<Coin> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "coin_genderator")
	@SequenceGenerator(name = "coin_generator", sequenceName = "coin_sequence", initialValue = 100)
	private Long id;
	@Min(message = "Number of coins cannot be negative", value = 0)
	private int count;
	@Min(message = "Value cannot be negative", value = 0)
	private double amount;
	@Size(min = 2, max = 10, message = "Description must be between {min} and {max} characters")
    @NotNull(message = "Description of the coin eg: R2")
	private String description;

	public Coin() {
        amount = 0;
        description = "";
	}

	public Coin(@Min(message = "Value cannot be negative", value = 0) double amount,
				@Size(min = 2, max = 10, message = "Description must be between {min} and {max} characters") String description, int count) {
		super();
		this.amount = amount;
		this.description = description;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(@NotNull String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description + amount / 100;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coin coin = (Coin) obj;
		return (coin.getAmount() == getAmount()
				&& coin.getDescription().equals(getDescription())
				&& coin.getCount() == getCount());

		// return true;
	}

	@Override
	public int hashCode() {
		int prime = 43;
		int result = 1;

		result = prime * result + (getId().hashCode());
		result = prime * result + Long.valueOf(Double.doubleToLongBits(getAmount())).hashCode();
		result = prime * result + getDescription().hashCode();

		return result;
	}

	@Override
	public int compareTo(Coin coin) {
		int BEFORE = -1;
		int EQUAL = 0;
		int AFTER = 1;

		if (this == coin) {
			return EQUAL;
		}

		if (amount < coin.getAmount()) {
			return BEFORE;
		}
		if (amount > coin.getAmount()) {
			return AFTER;
		}

		return EQUAL;

	}
}
