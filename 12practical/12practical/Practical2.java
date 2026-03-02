
import java.util.Random;

public class Practical2 {

    // O(n^3)
    public static int mcsOn3(int[] X) {
        int n = X.length;
        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                for (int r = low; r <= high; r++) {
                    sum += X[r];
                    if (sum > maxSoFar) {
                        maxSoFar = sum;
                    }
                }
            }
        }


        return maxSoFar;
    }

    public static int mcsOn2A(int[] X) {
        int n = X.length;
        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            int sum = 0;
            for (int r = low; r < n; r++) {
                sum += X[r];
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }

            }
        }
        return maxSoFar;
    }

    public static int mcsOn2B(int[] X) {
        int n = X.length;
        int[] sumTo = new int[n + 1];
        sumTo[0] = 0;

        for (int i = 1; i <= n; i++) {
            sumTo[i] = sumTo[i - 1] + X[i - 1];
        }
        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; low++) {
                int sum = sumTo[high + 1] - sumTo[low];
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;

    }

    public static int maxStraddle(int[] X, int low, int high) {
        int middle = (low + high) / 2;
        int sum = 0;
        int maxLeft = 0;

        for (int i = middle; i >= low; i--) {
            sum += X[i];
            if (sum > maxLeft) {
                maxLeft = sum;
            }
        }
        sum = 0;
        int maxRight = 0;
        for (int i = middle + 1; i <= high; i++) {
            sum += X[i];
            if (sum > maxRight) {
                maxRight = sum;
            }
        }

        return maxLeft = maxRight;
    }
    public static int mcsOnlogn(int[] X, int low,int high){
        if(low>high) return 0;
        if(low == high ) return Math.max(0, X[low]);

        int middle = (low + high)/2;

        int mLeft = mcsOnlogn(X, low, middle);
        int mRight = mcsOnlogn(X, middle +1, high);
        int mStraddle = maxStraddle(X, low,high);

        return Math.max(Math.max(mLeft, mRight),mStraddle);
    }
    public static int mcsOn(int[] X) {
        int N = X.length;
        int maxSoFar = 0;
        int maxToHere = 0;

        for (int i = 0; i < N; i++) {
            maxToHere = Math.max(maxToHere + X[i], 0);
            maxSoFar = Math.max(maxSoFar, maxToHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int n = 20;
        int[] X = new int[n];
        Random rand = new Random();

        int countP = 0;
        int countM = 0;

        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(n) + 1;
            if (rand.nextInt(2) == 0) {
                value = -value;
            }
            X[i] = value;

            if (value < 0) {
                countM++;
            } else {
                countP++;
            }
        }

        System.out.println("countM = " + countM + "  countP = " + countP);

        for (int x : X) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println(mcsOn3(X));
        System.out.println(mcsOn2A(X));
        System.out.println(mcsOn2B(X));
        System.out.println(mcsOn(X));
        // System.out.println(mcsOnlogn(X, 0, n - 1));

        System.out.println("--------------------");
    }
}


