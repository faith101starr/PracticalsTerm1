
import java.lang.Math;
import java.io.*;
import java.text.DecimalFormat;

// ======================
// NODE CLASS
// ======================
class Node {
    int key;
    String data;

    public Node(int key, String data) {
        this.key = key;
        this.data = data;
    }
}

// ======================
// MAIN CLASS
// ======================
public class timeMethods {

    public static int N = 32654;   // number of possible keys

    public static void main(String args[]) {

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double time;

        int repetition, repetitions = 30;

        // ======================
        // LOAD FILE INTO ARRAY
        // ======================
        Node[] array = new Node[N];
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("ulysses.numbered"));
            String line;

            while ((line = br.readLine()) != null && count < N) {
                int key = Integer.parseInt(line.substring(0,5).trim());
                String data = line.substring(5).trim();
                array[count] = new Node(key, data);
                count++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            return;
        }

        int n = count;   // actual number of records

        // ======================
        // TIMING VARIABLES
        // ======================
        double linearTime = 0, linearTime2 = 0;
        double binaryTime = 0, binaryTime2 = 0;
        double openTime = 0, openTime2 = 0;
        double chainTime = 0, chainTime2 = 0;

        // ======================
        // CREATE HASH TABLES
        // ======================

        OpenHash oh = new OpenHash(100_003);    //prime numbers table size
        ChainedHash ch = new ChainedHash(100_003);


        // ======================
        // INSERT DATA INTO HASH TABLES
        // ======================

        for (int i = 0; i < n; i++) {
            String keyStr = String.valueOf(array[i].key);
            oh.insert(keyStr, array[i].data);
            ch.insert(keyStr, array[i].data);
        }

        //=====================
        //EXPERIMENT LOOP
        //=====================

        for(repetition = 0; repetition < repetitions; repetition++) {

            int randomKey = 1 + (int) (Math.random() * N);
            String keyStr = String.valueOf(randomKey);

            // ---------- LINEAR SEARCH ----------
            /*
            start = System.currentTimeMillis();
            linearsearch(array, n, randomKey);
            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            linearTime += time;
            linearTime2 += (time * time);
            */

            // ---------- BINARY SEARCH ----------
            /*
            start = System.currentTimeMillis();
            binarysearch(array, n, randomKey);
            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            binaryTime += time;
            binaryTime2 += (time * time);
            */

            // --------OPEN HASH LOOKUP ----------

            start = System.currentTimeMillis();
            oh.lookup(keyStr);
            finish = System.currentTimeMillis();
            time = (double) (finish - start);
            openTime += time;
            openTime2 += time * time;

            //------- CHAINED HASH LOOKUP -------
            start = System.currentTimeMillis();
            ch.lookup(keyStr);
            finish = System.currentTimeMillis();
            chainTime += time;
            chainTime2 += time * time;
        }




        //====================================
        // STATISTICS CALCULATIONS
        //====================================

         /*

            double linearAve = linearTime / repetitions;
        double linearStd =
                Math.sqrt(linearTime2 - repetitions * linearAve * linearAve)
                        / (repetitions - 1);

        double binaryAve = binaryTime / repetitions;
        double binaryStd =
                Math.sqrt(binaryTime2 - repetitions * binaryAve * binaryAve)
                        / (repetitions - 1);
        */

        // Open Hash
        double openAve = openTime / repetitions;
        double openStd =
                Math.sqrt(openTime2 - repetitions * openAve * openAve)
                        / (repetitions - 1);

        // Chained Hash
        double chainAve = chainTime / repetitions;
        double chainStd =
                Math.sqrt(chainTime2 - repetitions * chainAve * chainAve)
                        / (repetitions - 1);

        // ======================
        // OUTPUT RESULTS
        // ======================
        System.out.println("\n\nStatistics");
        System.out.println("________________________________________________");

        /*System.out.println("Linear Average time = "
                + fiveD.format(linearAve/1000) + " s");
        System.out.println("Linear Std deviation = "
                + fourD.format(linearStd) + " ms");

        System.out.println();

        System.out.println("Binary Average time = "
                + fiveD.format(binaryAve/1000) + " s");
        System.out.println("Binary Std deviation = "
                + fourD.format(binaryStd) + " ms"); */

        System.out.println("Open Hash Average time = "+ fiveD.format(openAve/1000) + " s");
        System.out.println("Open Hash Std deviation = "
                + fourD.format(openStd) + " ms");

        System.out.println();

        System.out.println("Chained Hash Average time = "
                + fiveD.format(chainAve/1000) + " s");
        System.out.println("Chained Hash Std deviation = "
                + fourD.format(chainStd) + " ms");

        System.out.println("Repetitions = " + repetitions);
        System.out.println("n = " + n);
        System.out.println("________________________________________________");
    }

    // ======================
    // LINEAR SEARCH
    // ======================
    static int linearsearch(Node[] arr, int n, int key) {
        for(int i = 0; i < n; i++) {
            if(arr[i].key == key)
                return i;
        }
        return -1;
    }

    // ======================
    // BINARY SEARCH
    // ======================
    static int binarysearch(Node[] arr, int n, int key) {

        int low = 0;
        int high = n - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(arr[mid].key == key)
                return mid;
            else if(arr[mid].key < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
