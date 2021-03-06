package ru.otus;

import java.util.Arrays;
import java.util.Scanner;

import static ru.otus.HelperArray.printArray;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input an array length: ");
        int num = in.nextInt();
        System.out.printf("An array length is  %d \n", num);
        int[] arrayOfInt = HelperArray.initArray(num);
        int[] arrayOfIntSimpleSort = Arrays.copyOf(arrayOfInt, arrayOfInt.length);
        int[] arrayOfIntThreadSort;
        ShellSorter sorter = new ShellSorter();

        long startTime = System.nanoTime();
        sorter.sort(arrayOfIntSimpleSort);
        long endTime = System.nanoTime();

        System.out.printf("A sorted array is \n");
        printArray(arrayOfIntSimpleSort);
        System.out.printf("A time of sorting is %d \n", endTime - startTime);

        startTime = System.nanoTime();
        arrayOfIntThreadSort = ThreadsSorter.sort(arrayOfInt,  sorter, 4);
        endTime = System.nanoTime();

        System.out.printf("A thread-sorted array is \n");
        printArray(arrayOfIntThreadSort);
        System.out.printf("A time of threads-sorting is %d \n", endTime - startTime);


    }


}


