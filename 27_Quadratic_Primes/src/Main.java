public class Main {
    // See https://projecteuler.net/problem=27
    // Read README.md for explanation
    public static void main(String[] args) {
        int[] answer = new int[2];
        int highestVal = 0;
        for (int a = -999; a < 1000; a++) {
            for (int b = -1000; b <=  1000; b++) {
                int val = highestPrimeValueAmount(a, b);
                if (val > highestVal) {
                    highestVal = val;
                    answer = new int[] {a, b};
                }
            }
        }
        System.out.println("Prime string of " + highestVal + " at a = " + answer[0] + " and b = " + answer[1]);
        System.out.println("Product of a and b is " + answer[0] * answer[1]);
    }

    private static int highestPrimeValueAmount(int a, int b) {
        for (int n = 0; true; n++) if (!isPrime(n * n + a * n + b)) return n;
    }

    private static boolean isPrime(int n) {
        n = Math.abs(n);
        if (n == 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}