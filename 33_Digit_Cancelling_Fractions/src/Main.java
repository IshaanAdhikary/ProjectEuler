import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        HashSet<Fraction> digitCancellingFractions = new HashSet<>(4);
        Fraction.fillFactors();

        for (int sharedDigit = 1; sharedDigit < 10; sharedDigit++) {
            for (int digit2 = 1; digit2 < 10; digit2++) {
                for (int digit3 = 1; digit3 < 10; digit3++) {
                    addIfDigitCancellingFraction(sharedDigit, digit2, digit3, digitCancellingFractions);
                }
            }
        }

        Fraction fractionsProduct = new Fraction(1, 1);
        for (Fraction fraction : digitCancellingFractions) {
            fractionsProduct.multiply(fraction);
            fractionsProduct.simplify();
        }

        System.out.println(fractionsProduct.getDenominator());
    }

    private static void addIfDigitCancellingFraction(int sharedDigit, int digit2, int digit3, HashSet<Fraction> digitCancellingFractions) {
        Fraction fraction1 = new Fraction(digit2 * 10 + sharedDigit, digit3 * 10 + sharedDigit);
        Fraction fraction2 = new Fraction(digit2 * 10 + sharedDigit, sharedDigit * 10 + digit3);
        Fraction fraction3 = new Fraction(sharedDigit * 10 + digit2, digit3 * 10 + sharedDigit);
        Fraction fraction4 = new Fraction(sharedDigit * 10 + digit2, sharedDigit * 10 + digit3);
        Fraction fractionCancelled = new Fraction(digit2, digit3);

        if (fraction1.isProper() && fraction1.equals(fractionCancelled)) digitCancellingFractions.add(fraction1);
        if (fraction2.isProper() && fraction2.equals(fractionCancelled)) digitCancellingFractions.add(fraction2);
        if (fraction3.isProper() && fraction3.equals(fractionCancelled)) digitCancellingFractions.add(fraction3);
        if (fraction4.isProper() && fraction4.equals(fractionCancelled)) digitCancellingFractions.add(fraction4);
    }
}

class Fraction {
    // Sets of factors of numbers 0-99 (zero is buffer), where index and number match. Factors are only included once, even if they appear multiple times (HashSet)
    private static ArrayList<HashSet<Integer>> factors = new ArrayList<>(101);

    private int numerator;
    private int denominator;

    public Fraction(Fraction otherFraction) {
        this.numerator = otherFraction.numerator;
        this.denominator = otherFraction.denominator;
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException();
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static void fillFactors() {
        // Buffer at index 0 so all indices line up w/ their corresponding number
        factors.add(new HashSet<>());
        for (int i = 1; i < 100; i++) {
            factors.add(new HashSet<Integer>());
            int num = i;

            while (i != 2 && num % 2 == 0) {
                factors.get(i).add(2);
                num /= 2;
            }
            for (int j = 3; j <= num; j += 2) {
                while (num % j == 0) {
                    factors.get(i).add(j);
                    num /= j;
                }
            }
        }
    }

    public void multiply(Fraction otherFraction) {
        this.numerator = this.numerator * otherFraction.numerator;
        this.denominator = this.denominator * otherFraction.denominator;
    }

    public void simplify() {
        // If the number is greater than 100, we can't use our List of HashSets earlier as a shortcut
        if (numerator > factors.size() - 1 || denominator > factors.size() - 1) {
            while (numerator % 2 == 0 && denominator % 2 == 0) {
                numerator /= 2;
                denominator /= 2;
            }
            for (int factor = 3; factor * factor < numerator; factor += 2) {
                while (numerator % factor == 0 && denominator % factor == 0) {
                    numerator /= factor;
                    denominator /= factor;
                }
            }
            if (denominator % numerator == 0) {
                denominator /= numerator;
                numerator = 1;
            }
            return;
        }

        // If the number is less than 100, we can use the shortcut, and only reduce by the shared factors
        HashSet<Integer> sharedFactors = new HashSet<>(factors.get(numerator));
        sharedFactors.retainAll(factors.get(denominator));

        if (sharedFactors.isEmpty()) return;

        for (Integer factor : sharedFactors) {
            while (numerator % factor == 0 && denominator % factor == 0) {
                numerator /= factor;
                denominator /= factor;
            }
        }   
    }

    public int getDenominator() {
        return denominator;
    }

    public boolean isProper() {
        return numerator < denominator;
    }

    // Simplifies while checking if equal
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Fraction)) return false;

        Fraction thisFractionCopy = new Fraction(this);
        Fraction otherFraction = (Fraction) obj;

        thisFractionCopy.simplify();
        otherFraction.simplify();

        return (thisFractionCopy.numerator == otherFraction.numerator) && (thisFractionCopy.denominator == otherFraction.denominator);
    }
}