public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] input = {3, 6, 7, 11};
        new KokoEatingBananas().minEatingSpeed(input, 8);
    }

    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = 1;
        for (int p : piles) {
            maxSpeed = Math.max(maxSpeed, p);
        }

        while (minSpeed <= maxSpeed) {
            int target = (minSpeed + maxSpeed) / 2;
            int hoursSpent = hourSpentEatingBananas(piles, target);
            if (hoursSpent <= h) maxSpeed = target - 1;
            else minSpeed = target + 1;
        }
        return minSpeed;
    }

    private int hourSpentEatingBananas(int[] piles, int target) {
        int hourSpent = 0;
        for (int p : piles) {
            hourSpent += Math.ceil((double) p / target);
        }
        return hourSpent;
    }
}
