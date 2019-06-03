package com.vending.machine.model;

import java.io.Serializable;
import java.util.List;

public class PurchaseCoinPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private List<Coin> coins;

	public void setCoins(List<Coin> coins) {
		this.coins = coins;

	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public List<Coin> getCoins() {
		return coins;
	}

}
