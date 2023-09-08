import java.util.HashSet;

public class Main {
    final private static int[] validDigits = new int[] {1, 3, 7, 9};
    final private static int maxCount = 6; 

    public static void main(String[] args) throws Exception {
        int amount = checkDigits();

        System.out.println(amount);
    }

    // Checks every possible combination of the 4 digits 1, 3, 7, and 9 recursively. Evens and 5s will always result in a non prime number (except the 2 below) 
    private static int checkDigits() {
        int digitCount = 0;
        // Current digits is the amount of digit 1s at index 0, 3s at index 1, 7s at 2, etc.
        int[] currentDigits = new int[validDigits.length];
        // +1 for 2, the only circular prime to contain 2, +1 for 5, the only circular prime to contain 5, and -1 for 1, which is not prime.
        return checkDigitRecursive(currentDigits, 3, digitCount, 0) + 1;
    }
    
    private static int checkDigitRecursive(int[] currentDigits, int prevDigit, int digitCount, int amount) {
        for (int digit = 0; digit <= prevDigit; digit++) {
            currentDigits[digit]++;
            digitCount++;

            if (digitCount < maxCount) amount = checkDigitRecursive(currentDigits, digit, digitCount, amount);

            amount += areDigitsCircularPrime(currentDigits);
            
            digitCount--;
            currentDigits[digit]--;
        }

        return amount;
    }

    private static int areDigitsCircularPrime(int[] currentDigits) {
        HashSet<Integer> permutations = findDigitPermutations(currentDigits);
        int circularPrimeAmount = 0;

        int digitCount = 0;
        for (int amount : currentDigits) digitCount += amount;

        while (permutations.size() > 0) {
            // Gets any permutation, order doesn't matter
            int permutation = permutations.iterator().next();
            // Use this to find the change in removed permutations, which is the amount of circular primes that can be made of these digits.
            int prevPermutationsAmount = permutations.size();
            if (isCircularPrime(permutation, digitCount, permutations)) {
                circularPrimeAmount += prevPermutationsAmount - permutations.size();
            }
        }
        return circularPrimeAmount;
    }

    // Removes circular values from the Hashset while checking
    private static boolean isCircularPrime(int x, int digitCount, HashSet<Integer> permutations) {
        boolean allPrime = true;
        for (int i = 0; i < digitCount; i++) {
            permutations.remove(x);
            if (!isPrime(x)) allPrime = false;
            x = (10 * x + x / (int) Math.pow(10, digitCount - 1)) % (int) Math.pow(10, digitCount);
        }
        return allPrime;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    private static HashSet<Integer> findDigitPermutations(int[] currentDigits) {
        HashSet<Integer> allPermutations = new HashSet<>();
        for (int i = 0; i < currentDigits.length; i++) {
            if (currentDigits[i] == 0) continue;

            currentDigits[i]--;
            findDigitPermuationsRecursive(currentDigits, validDigits[i], allPermutations);
            currentDigits[i]++;
        }
        return allPermutations;
    }
    
    private static void findDigitPermuationsRecursive(int[] currentDigits, int currentPermutation, HashSet<Integer> allPermutations) {
        boolean noDigitsLeft = true;
        for (int i = 0; i < currentDigits.length; i++) {
            if (currentDigits[i] == 0) continue;
            noDigitsLeft = false;

            currentDigits[i]--;
            currentPermutation = currentPermutation * 10 + validDigits[i];
            findDigitPermuationsRecursive(currentDigits, currentPermutation, allPermutations);
            currentPermutation = currentPermutation / 10;
            currentDigits[i]++;
        }
        if (noDigitsLeft) allPermutations.add(currentPermutation);
    }
}