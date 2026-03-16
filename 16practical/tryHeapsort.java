import java.io.*;
import java.util.*;


public class tryHeapsort{

    //The temporary heap used for top-down construction

    static String [] heap;

    //The number of elements currently in the heap

    public static void main (String [] args) throws Exception {

        //Reading sorted list from the cleaned file anagrams_output.txt

        String[] words = readkeys("anagrams_output.txt");

        // BOTTOM-UP HEAP

        String[] bottomHeap = words.clone();

        long startBottom = System.nanoTime();

        buildHeapBottomUp(BottomHeap);
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

    static String[] readKeys(String filename) throws Exception{
        List<String>keys = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader (filename))
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

    //----------------------------------------
    //BOTTOM-UP HEAP CONSTRUCTION


        }


    }


        }