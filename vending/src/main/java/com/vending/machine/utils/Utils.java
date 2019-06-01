package com.vending.machine.utils;

import java.util.List;

import com.vending.product.model.Coin;

public class Utils {
	public static boolean isAcceptable(List<Coin> coins, List<Coin> systemCoins) {
		boolean acceptable = true;
		for (var coin : coins) {
			if (!systemCoins.contains(coin))
				return false;
		}

		return acceptable;

	}
}
