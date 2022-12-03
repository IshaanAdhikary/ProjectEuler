public class Main {
    public static void main(String[] args) {
        int[] fifthPowersOfDigits = createFifthPowerArray();

        // Start at negative 1 since 1 = 1^5 will trigger the below loop, but the problem tells us to ignore it, since it's not a sum.
        int answer = -1;

        // 354294 is just 9^5 * 6. This is the maximum possible number, since 9^5 * 7 is only 6 digits long, and after that the number only grows faster than the power sum.
        // While possible to algorithimically calculate, there's no need for it this problem, since it's pretty simple.
        for (int number = 0; number <= 354294; number++) {
            if (number == sumOfDigitPowers(number, fifthPowersOfDigits)) answer += number;
        }

        System.out.println("The sum of such numbers is: " + answer);
    }

    private static int sumOfDigitPowers(int num, int[] powArray){ 
        int sum = 0;

        // For each digit (num % 10), get it's value as a power of 5 and add it to running total for total sum
        while (num > 0) {
            sum += powArray[num % 10];
            num /= 10;
        }

        return sum;
    }

    // Since we're using 1^5, 2^5, 3^5, etc. so much, just save the answers in memory instead, where index 0 is 0^5, etc.
    private static int[] createFifthPowerArray() {
        int[] result = new int[10];

        for (int i = 0; i < 10; i++) {
            result[i] = (int) Math.pow(i, 5);
        }
        
        return result;
    }
}
