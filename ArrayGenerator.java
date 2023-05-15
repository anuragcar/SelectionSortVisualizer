import java.util.Random;

public class ArrayGenerator {
    private static Random generator = new Random();
    public static int[] randomIntArray(int length, int n) {
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.nextInt(n);
        }
        return a;
    }
}