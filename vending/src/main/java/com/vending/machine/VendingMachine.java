package com.vending.machine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.vending.machine.model.Coin;
import com.vending.machine.model.Product;
import com.vending.machine.utils.Utils;

public class VendingMachine {
	private final Map<Product, Integer> inventory;
	private final Map<Coin, Integer> initialCoins;
	private double balance;
	private static final Coin[] array = { new Coin(10, "TENC"), new Coin(50, "FIFTY"), new Coin(100, "R1"),
			new Coin(500, "R5") };
	private List<Coin> acceptableCoins = Arrays.asList(array);

	public VendingMachine(Map<Product, Integer> newStockList, Map<Coin, Integer> coins) {
		this.inventory = newStockList;
		this.initialCoins = coins;

		init();
	}

	private void init() {
		for (Map.Entry<Coin, Integer> coin : initialCoins.entrySet()) {
			double amount = coin.getKey().getAmount();
			for (int i = 0; i < coin.getValue(); i++) {
				balance += amount;
			}
		}
	}

	public double getBalance() {
		return balance;
	}

	public Map<Product, Integer> getInventory() {
		return inventory;
	}

	public void purchase(Product item, List<Coin> coins) {
		if (item == null || coins == null) {
			return;
		}

		if (!Utils.isAcceptable(coins, acceptableCoins))
			return;

		// --- update products
		for (Map.Entry<Product, Integer> items : inventory.entrySet()) {
			Product product = items.getKey();
			int total = items.getValue();

			if (total < 1)
				return;
			double amount = 0;
			for (Coin coin : coins)
				amount += coin.getAmount();

			if (product == item && amount >= product.getPrice()) {
				items.setValue(items.getValue() - 1);

				// --- update coins

				// --- update balance
				updateBalance(coins);

				if (amount > product.getPrice())
					makeChange(product.getPrice(), amount);
			}
		}
	}

	public void updateBalance(double amount) {
		if (amount <= 0) {
			return;
		}

	}

	private void updateBalance(List<Coin> coins) {
		if (coins == null) {
			return;
		}

		for (Coin coin : coins) {
			updateBalance(coin);
		}
	}

	private void updateBalance(Coin coin) {
		if (coin == null) {
			return;
		}

		balance += coin.getAmount();
	}

	public List<Coin> makeChange(double price, double amount) {
		if (amount <= 0) {
			return Collections.emptyList();
		}

		List<Coin> list = Collections.emptyList();

		// --- convert amount to coins

		return list;
	}
}