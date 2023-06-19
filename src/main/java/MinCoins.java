import java.util.Arrays;

public class MinCoins {
    public static void main(String[] args) {
        int[] coins = {25, 10, 5, 1};

        System.out.println(coinChangeRecursion(32, coins));
    }

    public static int coinChangeRecursion(int amount, int[] coins) {
        int[] cache = new int[amount];
        for (int i = 0; i < amount; i++) {
            cache[i] = -1;
        }
        Arrays.sort(coins);
        return coinChangeRecursion(amount, coins, cache);
    }

    public static int coinChangeRecursion(int amount, int[] coins, int[] cache) {
        if (amount == 0) return 0;
        int minCoins = amount;
        boolean found = false;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                found = true;
                int diffAmount = amount - coin;
                int count;
                if (cache[diffAmount] >= 0) {
                    count = cache[diffAmount];
                } else {
                    count = coinChangeRecursion(diffAmount, coins, cache);
                }
                if (count == -1) {
                    return -1;
                }
                minCoins = Math.min(minCoins, count + 1);
            }
        }
        if (!found) {
            return -1;
        }
        return minCoins;
    }

    //Preferred
    public static int coinChangeDP(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int[] cache = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            cache[i] = Integer.MAX_VALUE;
            for(int coin : coins){
                if (coin <= i && cache[i - coin] != Integer.MAX_VALUE) {
                    cache[i] = Integer.min(cache[i], 1 + cache[i - coin]);
                }
            }

        }
        if (cache[amount] == Integer.MAX_VALUE)
            return -1;
        else
            return cache[amount];
    }
}
