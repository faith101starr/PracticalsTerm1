import java.util.Random;
import java.util.Arrays;

public class ShuffleMethods {
    public static int[] slowShuffle(int N) {

        int[] shuffled = new int[N];

        boolean[] isNotPresent = new boolean[N + 1];

        Arrays.fill(isNotPresent, true);

        Random rand = new Random();

        int i = -1;

        while (i < N - 1) {
            int r = rand.nextInt(N) + 1;

            if (isNotPresent[r]) {
                i += 1;

                shuffled[i] = r;
                isNotPresent[r] = false;

            }
        }

        return shuffled;
    }

    public static int[] biasedShuffle(int N) {

        int[] shuffled = new int[N];

        boolean[] isNotPresent = new boolean[N + 1];

        Arrays.fill(isNotPresent, true);

        Random rand = new Random();

        int i = -1;

        while (i < N - 1) {
            int r = rand.nextInt(N) + 1;
            if (isNotPresent[r]) {
                i += 1;
                shuffled[i] = r;
                isNotPresent[r] = false;

            } else {
                r = rand.nextInt(N) + 1;
            }

        }
        return shuffled;
    }

    public static int[] shuffle(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = i + 1;

        Random rand = new Random();
        for (int i = N - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

        }
        return arr;
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int N = 10;

        System.out.println("Slow shuffle: ");
        printArray(slowShuffle(N));

        System.out.println("Biased shuffle: ");
        printArray(biasedShuffle(N));

        System.out.println("Unbiased shuffle: ");
        printArray(shuffle(N));

    }
}












