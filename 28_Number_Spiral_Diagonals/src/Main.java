public class Main {
    public static void main(String[] args) {
        int total = 1;
        for (int ring = 2; 2 * ring - 1 <= 1001; ring++) {
            total += 16 * ring * ring - 28 * ring + 16;
        }
        System.out.println("The sum of the diagonals is " + total);
    }
}
