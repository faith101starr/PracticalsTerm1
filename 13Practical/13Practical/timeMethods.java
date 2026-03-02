//4561731 KF Ngwepe CSC 211
import org.w3c.dom.ls.LSOutput;

import java.lang.Math;
import java.io.*;
import java.text.DecimalFormat;


//=============
//NODE CLASS
//=============

class Node{
    int key;
    String data;

    public Node (int key, String data){
        this.key = key;
        this.data = data;

    }
}

//============
//MAIN CLASS
//============

public class timeMethods{

    public static int N= 32654;  //number of possible keys

        public static void main(String args[]) {
            DecimalFormat twoD = new DecimalFormat("0.00");

            DecimalFormat fourD = new DecimalFormat("0.0000");

            DecimalFormat fiveD = new DecimalFormat("0.00000");

            long start, finish;
            double time;

            int repetition, repetitions = 30;

            //=================
            //LOAD FILE INTO ARRAY
            //=================

            Node[] array = new Node [N];

            int count =0;

            try{
                BufferedReader br = new BufferedReader(new FileReader ("ulysses.numbered"));

                String line;

                while ((line = br.readLine()) != null && count <N) {
                    int key = Integer.parseInt(line.substring(0,5).trim());
                    String data = line.substring(5).trim();
                    array[count] = new Node(key , data);
                    count++;
                }
                br.close();
            }catch (Exception e) {
                System.out.println("Error reading file");
                return;
            }
            int n = count; //Actual number of records

            //================
            //TIMING VARIABLES
            //================

                double linearTime = 0,linearTime2 = 0;
            double binaryTime = 0,BinaryTime2 = 0;

            //================
            //EXPERIMENT LOOP
            //================

            double binaryTime2;
            for (repetition =0; repetition < repetitions; repetition++) {
                int randomKey = 1 + (int) (Math.random() * 32654);

                //-------LINEAR SEARCH-------//

                start = System.currentTimeMillis();
                linearsearch(array, n, randomKey);
                finish = System.currentTimeMillis();

                time = (double) (finish - start);
                linearTime += time;
                linearTime2 += (time * time);

                //------Binary Search-----//

                start = System.currentTimeMillis();
                binarysearch(array, n, randomKey);
                finish = System.currentTimeMillis();

                time = (double) (finish - start);
                binaryTime += time;
                binaryTime2 += (time * time);
            }
            //=================
            //Statistical calculations//
            //=================

            double linearAve = linearTime/repetitions;
            double linearStd = Math.sqrt(linearTime2 - repetitions * linearAve * linearAve)/(repetitions -1);

            double binaryAve = binaryTime/ repetitions;
            double binaryStd = Math.sqrt(binaryTime2 - repetitions * binaryAve * binaryAve)/ (repetitions -1);

            //==================
            //OUTPUT
            //===============

            System.out.println("\n\nStatistics");
            System.out.println("______________________");
            System.out.println("Linear Average time = " +  fiveD.format(linearAve/1000) + " s");
            System.out.println("Linear Std deviation = " + fourD.format(linearStd));

            System.out.println();

            System.out.println("Binary Average time = " +fiveD.format(binaryAve/1000 + "s"));

            System.out.println("Binary Std deviation = " + fourD.format(binaryStd) + "ms" );

            System.out.println("Repetitions =  " +  repetitions );
            System.out.println("n = " + n);

            //Linear search//

            static int linearsearch (Node[] arr, int n, int key){
                for(int i = 0; i <n;i++)









                }





        }


    }

}