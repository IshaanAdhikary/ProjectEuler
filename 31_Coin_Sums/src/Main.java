public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(getCoinCombinations(200, new int[] {1, 2, 5, 10, 20, 50, 100, 200}));
    }

    private static Integer getCoinCombinations(int totalPence, int[] coins) {
        int[] possibleCombinations = new int[totalPence + 1];

        // No coins
        possibleCombinations[0] = 1;
        for (int i = 1; i <= totalPence; i++) possibleCombinations[i] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < possibleCombinations.length; j++) {
                possibleCombinations[j] += possibleCombinations[j - coins[i]];
            }
        }

        return possibleCombinations[totalPence];
    }
}
