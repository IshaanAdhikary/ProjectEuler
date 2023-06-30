public class Main {
    // Manually calculated limit
    final private static int maxCount = 7;
    final private static int[] digitFactorials = new int[10];

    public static void main(String[] args) throws Exception {
        fillFactorials();
        int total = checkDigits();

        // Exclude 1 and 2
        total -= 3;

        System.out.println(total);
    }

    private static int checkDigits() {
        int digitCount = 0;
        // Current digits is the amount of digit 0s at index 0, 1s at index 1, etc.
        int[] currentDigits = new int[10];
        return checkDigitRecursive(currentDigits, 9, digitCount, 0);
    }

    private static int checkDigitRecursive(int[] currentDigits, int prevDigit, int digitCount, int total) {
        for (int digit = 0; digit <= prevDigit; digit++) {
            currentDigits[digit]++;

            digitCount++;
            if (digitCount <= maxCount) total = checkDigitRecursive(currentDigits, digit, digitCount, total);

            // Full set of digits

            total += addIfDigitsValid(currentDigits);
            
            digitCount--;
            currentDigits[digit]--;
        }

        return total;
    }

    private static int addIfDigitsValid(int[] currentDigits) {
        int factorialTotal = 0;
        for (int i = 0; i < currentDigits.length; i++) factorialTotal += digitFactorials[i] * currentDigits[i];

        char[] totalDigitsChar = Integer.toString(factorialTotal).toCharArray();
        int[] totalDigits = new int[10];
        for (char c : totalDigitsChar) {
            totalDigits[Character.getNumericValue(c)]++;
        }

        boolean sameValue = true;
        for (int i = 0; i < currentDigits.length; i++) {
            if (currentDigits[i] != totalDigits[i]) {
                sameValue = false;
                break;
            }
        }

        return sameValue ? factorialTotal : 0;
    }

    private static void fillFactorials() {
        digitFactorials[0] = 1;
        for (int i = 1; i < digitFactorials.length; i++) digitFactorials[i] = digitFactorials[i - 1] * i;
    }
}