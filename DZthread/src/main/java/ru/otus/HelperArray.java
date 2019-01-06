package ru.otus;

import java.util.Arrays;

public class HelperArray {

    public static int[] initArray(int lengthArray){

        int[] arrayOfInt = new int[lengthArray];
        for (int i = 0; i < arrayOfInt.length; i++)
            arrayOfInt[i] = (int) ( Math.random() * lengthArray);
        System.out.printf("An array is \n");
        System.out.println(Arrays.toString(arrayOfInt));
        System.out.printf(" \n");
        return arrayOfInt;
    }

    public static void printArray(int[] arrayInt){
        System.out.println(Arrays.toString(arrayInt));
        System.out.printf(" \n");
    }

    public static int[] mergeArrays(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        int i = 0;

        if (a.length == 0)
            return b;

        if (b.length == 0)
            return a;

        if (result.length == 0)
            return result;

        while (i < result.length) {
            result[i] = a[aIndex] < b[bIndex] ? a[aIndex++] : b[bIndex++];
            if (aIndex == a.length) {
                System.arraycopy(b, bIndex, result, ++i, b.length - bIndex);
                break;
            }
            if (bIndex == b.length) {
                System.arraycopy(a, aIndex, result, ++i, a.length - aIndex);
                break;
            }
            i++;
        }
        return result;
    }

    public static int[][] mergeArrays(int[][] a){

        int[][] tempArray = Arrays.copyOf(a, a.length);

        if (tempArray.length != 2)
        {
            int[][] tempArray1 = new int[tempArray.length/2][];
            for (int i = 0; i < tempArray.length; i = i + 2){
                tempArray1[i/2] = mergeArrays(a[i], a[i+1]);
            }
            tempArray = mergeArrays(tempArray1);
        }
        return tempArray;
    }

}
