import java.io.*;
import java.util.*;


public class tryHeapsort{

    static int size;

    //The temporary heap used for top-down construction

    static String [] heap;

    //The number of elements currently in the heap

    public static void main (String [] args) throws Exception  {

        //Reading sorted list from the cleaned file anagrams_output.txt

        String[] words = readKeys("anagrams_output.txt");

        System.out.println("Total words read: " + words.length);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("HEAPSORT COMPARISON");
        System.out.println("=".repeat(60));

        System.out.println("\n First 20 words before sorting");
        
        String [] testWords = Arrays.copyOfRange(words, 0,Math.min(20, words.length));
        
        printWords(testWords , 20);

        String[] testBottom = testWords.clone();
        long startTestBottom = System.nanoTime();
        buildHeapBottomUp(testBottom);
        heapSort(testBottom);
        long endTestBottom = System.nanoTime();


        System.out.println("\n Bottom-Up (20 words) - Sorted:");

        printWords(testBottom,20);

        System.out.println("   Time: " + (endTestBottom - startTestBottom) + " ns");


        // BOTTOM-UP HEAP

        String[] bottomHeap = words.clone();

        long startBottom = System.nanoTime();

        buildHeapBottomUp(bottomHeap);
        heapSort(bottomHeap);

        long endBottom = System.nanoTime();

        System.out.println("Bottom-Up Sorted keys :");

        printArray(bottomHeap);
        System.out.println("Bottom-Up Time: " + (endBottom - startBottom) + " ns");

        //TOP-DOWN HEAP

        String[] topHeap = words.clone();

        long startTop = System.nanoTime();

        buildHeapTopDown(topHeap);
        heapSort(topHeap);

        long endTop = System.nanoTime();

        System.out.println("\nSorted keys (Top-Down): ");
        printArray(topHeap);
        System.out.println("Top-Down Time: " + (endTop - startTop) + " ns");

    }

    private static void printWords(String[] testWords, int i) {
    }

    static String[] readKeys(String filename) throws Exception{
        List<String>keys = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader (filename));
                String line;

        while((line = reader.readLine()) != null){
            int colon = line.indexOf(":");

            if (colon != -1) {
                String key = line.substring(0, colon);
                keys.add(key);
            }
        }

        reader.close();

        return keys.toArray(new String[0]);
    }

    // -----------------------------
    // Bottom-Up Heap Construction
    // -----------------------------
    static void buildHeapBottomUp(String[] arr) {

        size = arr.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }


    // -----------------------------
    // Top-Down Heap Construction
    // -----------------------------
    static void buildHeapTopDown(String[] arr) {

        heap = new String[arr.length];
        size = 0;

        for (String word : arr) {
            insert(word);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap[i];
        }
    }


    // Insert a new element into the heap
    static void insert(String value) {

        heap[size] = value;
        int i = size;
        size++;

        while (i > 0) {

            int parent = (i - 1) / 2;

            if (heap[i].compareTo(heap[parent]) <= 0)
                break;

            String temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;

            i = parent;
        }
    }


    // -----------------------------
    // Heapify (restore heap order)
    // -----------------------------
    static void heapify(String[] arr, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left].compareTo(arr[largest]) > 0)
            largest = left;

        if (right < size && arr[right].compareTo(arr[largest]) > 0)
            largest = right;

        if (largest != i) {

            String temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, largest);
        }
    }


    // -----------------------------
    // Heap Sort
    // -----------------------------
    static void heapSort(String[] arr) {

        size = arr.length;

        for (int i = size - 1; i > 0; i--) {

            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            size--;

            heapify(arr, 0);
        }
    }


    // Print the contents of an array
    static void printArray(String[] arr) {

        for (String word : arr) {
            System.out.print(word + " ");
        }

        System.out.println();
    }
}








