package com.vending.machine.utils;

import com.vending.machine.model.Coin;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {
	public static boolean isAcceptable(List<Coin> coins, List<Coin> systemCoins) {
		boolean acceptable = true;
		for (var coin : coins) {
            if (!systemCoins.contains(coin)) {
                return false;
            }
		}

		return acceptable;
	}

    public static double getAmount(List<Coin> coins) {
        double amount = 0;
        for (Coin coin : coins) {
            amount += coin.getAmount();
        }

        return amount;
    }

    public static List<Coin> toCoin(List<Coin> systemCoins, Double amount) {
        if (amount <= 0) {
            return Collections.emptyList();
        }

        List<Coin> coins = Collections.emptyList();
        // --- sort from Large to Small. See the compareTo(T, T)
        Collections.sort(systemCoins);

        for (Coin coin : systemCoins) {
            if (amount > coin.getAmount()) {
                int money = (int) (amount / coin.getAmount());
                IntStream.range(0, money).mapToObj(i -> coin).forEachOrdered(coins::add);

                amount -= money;
            }
        }

        return coins;

    }
}
