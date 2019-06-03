package com.vending.machine.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "purchase")
public class Purchase {
	@Embedded
	@JsonIgnore
	private PurchaseCoinPK pk;

	@Column(nullable = false)
	private Integer quantity;

	public Purchase() {
		super();
	}

	public Purchase(List<Coin> coins, Product product, Integer quantity) {
		pk = new PurchaseCoinPK();
		pk.setCoins(coins);
		pk.setProduct(product);
		this.quantity = quantity;
	}

	public Product getProduct() {
		// TODO Auto-generated method stub
		return pk.getProduct();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public PurchaseCoinPK getPk() {
		return pk;
	}

	public void setPk(PurchaseCoinPK pk) {
		this.pk = pk;
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
		Purchase other = (Purchase) obj;
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}

		return true;
	}

}
