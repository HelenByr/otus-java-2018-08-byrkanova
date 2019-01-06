package ru.otus;

import java.util.Arrays;
import static ru.otus.HelperArray.mergeArrays;
import static ru.otus.HelperArray.printArray;

public class ThreadsSorter {


    public static int[] sort(int[] arrayOfInt, SerialIntSorter sorter, int countThreads) {

        int[][] arrayOfIntThreadSort = new int[countThreads][];
        Thread[] t = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            int finalI = i;
            arrayOfIntThreadSort[finalI] = Arrays.copyOfRange(arrayOfInt, i * arrayOfInt.length / countThreads, (i + 1) * arrayOfInt.length / countThreads);
            t[i] = new Thread(() -> {
                sorter.sort(arrayOfIntThreadSort[finalI]);
            }
            );
            t[i].start();
        }

        for (int i = 0; i < countThreads; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int[][] rezult1 = mergeArrays(arrayOfIntThreadSort);
        int[] rezult = mergeArrays(rezult1[0], rezult1[1]);
        return rezult;

    }

}
