import java.io.*;
import java.util.*;


public class tryHeapsort{

    //The temporary heap used for top-down construction

    static String [] heap;

    //The number of elements currently in the heap

    public static void main (String [] args) throws Exception{

        //Reading sorted list from the cleaned file anagrams_output.txt

        String[] words = readkeys("anagrams_output.txt");

        // BOTTOM-UP HEAP

        String [] bottomHeap = words.clone();

        long stratBottom = System.nanoTime();

        buildHeapBottomUp(BottomHeap);
        heapSort(bottomHeap);

        long startBottom = System.nanoTime();

        

    }


        }