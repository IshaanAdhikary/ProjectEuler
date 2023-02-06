import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        HashSet<Integer> pandigitalProducts = new HashSet<>();

        int factor = 1;

        // If the factor has more than four digits, then it's impossible to be pandigital.
        // i.e. a five digit number means at least a five digit answer (at least 11 total, with a single digit factor)
        // but four digits could still work (exactly 9 with a single digit factor)
        // Increment through all possible factors, checking each factor for those factors to see if pandigital
        while (factor < 10000) {
            testForPandigitals(factor, pandigitalProducts);
            factor++;
        }

        int total = 0;
        for (Integer pandigitalProduct : pandigitalProducts) total += pandigitalProduct;
        System.out.println(total);
    }

    // Search with a second factor incrementing upward; if there are more than nine digits, this factor produces no more pandigital products with larger numbers
    private static void testForPandigitals(int bigFact, HashSet<Integer> pandigitalProducts) {
        for (int smallFact = 0; smallFact < bigFact; smallFact++) {
            int product = smallFact * bigFact;
            String allDigits = "" + smallFact + bigFact + product;

            if (allDigits.length() > 9) return;

            if (testIfPandigitalProduct(allDigits)) {
                pandigitalProducts.add(product);
            }
        }
        return;
    }
    
    private static boolean testIfPandigitalProduct(String allDigits) {
        HashSet<Character> characters = new HashSet<>();

        for (int i = 0; i < allDigits.length(); i++) characters.add(allDigits.charAt(i));

        // If the characters have 9 unique digits excluding zero, they are pandigital
        if (characters.contains('0')) return false;
        if (characters.size() == 9) return true;
        return false;
    }
}
