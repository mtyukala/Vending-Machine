package com.vending.product.model;

public class Coin {
	// enum coinType{PAPER, COIN}
	private double amount;
	private String description;

	public Coin() {
		this.amount = 0;
		this.description = "";
	}

	public Coin(double amount, String desc) {
		this.amount = amount;
		this.description = desc;
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
