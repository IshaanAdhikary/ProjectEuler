import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int answer = amountOfDistinctPowers(100, 100);
        System.out.println("There are " + answer + " distinct terms.");
    }

    private static Integer amountOfDistinctPowers(int maxBase, int maxPower) {
        if (maxBase < 2 || maxPower < 2) throw new IllegalArgumentException("Bases and powers of 0, 1, or negative numbers are invalid.");

        HashSet<Double> distinctPowers = new HashSet<>();

        for (int base = 2; base <= maxBase; base++){
            for (int power = 2; power <= maxPower; power++) {
                distinctPowers.add(Math.pow(base, power));
            }
        }

        return distinctPowers.size();
    }
}
