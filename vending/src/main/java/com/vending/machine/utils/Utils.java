package com.vending.machine.utils;

import com.vending.machine.model.Coin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Utils {
    // --- one unit of currency (Rands) is 100 cents
    public static final int OneUnit = 100;

    private static final Logger logger = Logger.getLogger(Utils.class.getCanonicalName());

    public static boolean isAcceptable(@NotNull List<Coin> coins, List<Coin> systemCoins) {

        for (Coin coin : coins) {
            if (!systemCoins.contains(coin)) {
                logger.warning("coin = " + coin.getDescription() + " is not acceptable");
                return false;
            }
		}

        return true;
	}

    public static double getAmount(List<Coin> coins) {
        double amount = 0;
        for (Coin coin : coins) {
            amount += coin.getAmount();
        }

        return amount;
    }

    /**
     * Converts the given amount to coins. First the method divides the given amount into Rands and then go through the list of
     * system coins and divide the given amount with the value of the individual coins if the amount is greater than the coin amount
     *
     * @param systemCoins
     * @param amount
     * @return
     * @see Coin compareTo(T, T)
     */
    public static List<Coin> toCoin(List<Coin> systemCoins, Double amount) {
        if (amount <= 0) {
            return Collections.emptyList();
        }
        logger.info("system coins = " + systemCoins.toString());
        List<Coin> coins = new ArrayList<>();
        Collections.sort(systemCoins);// --- sort from Large to Small. See the Coin.compareTo(T, T)

        //amount /= OneUnit; // convert to rands
        for (Coin coin : systemCoins) {
            //double coinInRand = coin.getAmount() / coin.getCount();
            if (amount > coin.getAmount()) {
                logger.info("Amount to convert = " + amount);

                int money = (int) (amount / coin.getAmount());
                logger.info("Coins = " + money + " in rands " + coin.getAmount() / OneUnit);

                if (money > 0) {
                    Coin newCoin = new Coin();
                    newCoin.setCid(coin.getCid());
                    newCoin.setCount(money);
                    newCoin.setAmount(coin.getAmount());
                    newCoin.setDescription(coin.getDescription());

                    logger.info("Number of coins to add = " + newCoin.getCount());
                    coins.add(newCoin);
                    Double dd = money * coin.getAmount();
                    logger.info("to subtract = " + dd);
                    amount -= dd;
                    logger.info("Coins in the list = " + coins.size());
                    logger.info("Amount Remaining = " + amount);
                }
            }
        }

        return coins;

    }
}
