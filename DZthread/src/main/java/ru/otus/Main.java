package ru.otus;

import com.google.common.collect.ObjectArrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Input an array length: ");
        int num = in.nextInt();

        System.out.printf("An array length is  %d \n", num);

        int[] arrayOfInt = new int[num];

        for (int i = 0; i < arrayOfInt.length; i++)
            arrayOfInt[i] = (int) ( Math.random() * num);
        System.out.printf("An array is \n");
        System.out.println(Arrays.toString(arrayOfInt));
        System.out.printf(" \n");


        int[] arrayOfIntSimpleSort = Arrays.copyOf(arrayOfInt, arrayOfInt.length);
        long startTime = System.nanoTime();
        shellSort(arrayOfIntSimpleSort);
        long endTime = System.nanoTime();

        System.out.printf("A sorted array is \n");
        System.out.println(Arrays.toString(arrayOfIntSimpleSort));
        System.out.printf(" \n");
        System.out.printf("A time of sorting is %d \n", endTime - startTime);


        startTime = System.nanoTime();
        int[] arrayOfIntThreadSort1 = Arrays.copyOf(arrayOfInt, arrayOfInt.length / 4);
        int[] arrayOfIntThreadSort2 = Arrays.copyOfRange(arrayOfInt, arrayOfInt.length / 4, arrayOfInt.length / 2);
        int[] arrayOfIntThreadSort3 = Arrays.copyOfRange(arrayOfInt, arrayOfInt.length / 2, 3* arrayOfInt.length / 4);
        int[] arrayOfIntThreadSort4 = Arrays.copyOfRange(arrayOfInt, 3* arrayOfInt.length / 4, arrayOfInt.length);

        Thread t1, t2, t3, t4;
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(arrayOfIntThreadSort1);
            }
        });
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(arrayOfIntThreadSort2);
            }
        });
        t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(arrayOfIntThreadSort3);
            }
        });
        t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(arrayOfIntThreadSort4);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] rezult1 = merge(arrayOfIntThreadSort1, arrayOfIntThreadSort2);
        int[] rezult2 = merge(arrayOfIntThreadSort3, arrayOfIntThreadSort4);
        int[] rezult = merge(rezult1, rezult2);

        endTime = System.nanoTime();
        System.out.printf("A thread-sorted array is \n");
        System.out.println(Arrays.toString(arrayOfIntSimpleSort));
        System.out.printf(" \n");
        System.out.printf("A time of threads-sorting is %d \n", endTime - startTime);
    }


    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        int i = 0;

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


    public static void shellSortFourThread(int[] a)
    {
        int temp;
        int h = 0;//величина интервала
        //вычисляем исходное значение интервала
        while(h <= a.length/4)
            h = 4*h + 1;


        for(int k = h; k > 0; k = (k-1)/4)
            for(Object i = k; ((Integer) i).intValue() < a.length; i = ((Integer) i).intValue() + 1)
            {

                    temp = a[((Integer) i).intValue()];
                    Object j;

                        for(j = ((Integer) i).intValue(); ((Integer) j).intValue() >= k; j = ((Integer) j).intValue() - k)
                            synchronized (j)
                                {
                                    if(temp < a[((Integer) j).intValue() - k])
                                        a[((Integer) j).intValue()] = a[((Integer) j).intValue() - k];
                                    else
                                        break;
                                }
                                a[((Integer) j).intValue()] = temp;




            }
    }


    public static void shellSort(int[] a)
    {
        int temp;
        int h = 0;//величина интервала
        //вычисляем исходное значение интервала
        while(h <= a.length/3)
            h = 3*h + 1;

        for(int k = h; k > 0; k = (k-1)/3)
            for(int i = k; i < a.length; i++)
            {
                temp = a[i];
                int j;
                for(j = i; j >= k; j -= k)
                {
                    if(temp < a[j - k])
                        a[j] = a[j - k];
                    else
                        break;
                }
                a[j] = temp;
            }
    }
}


