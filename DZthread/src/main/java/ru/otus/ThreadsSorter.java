package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;

import static ru.otus.HelperArray.mergeArrays;
import static ru.otus.HelperArray.printArray;
import static ru.otus.Sorter.shellSort;

public class ThreadsSorter {


    public static int[] sort(int[] arrayOfInt, Class<Sorter> sorterClass, int countThreads) {

        Method[] methods = sorterClass.getMethods();
        Method shellMethod = null;
        for (Method m : methods) {
            if ("shellSort".equals(m.getName())) {
                shellMethod = m;
                break;
            }
        }
        Method finalShellMethod = shellMethod;
        int[][] arrayOfIntThreadSort = new int[countThreads][];
        Thread[] t = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            int finalI = i;
            arrayOfIntThreadSort[finalI] = Arrays.copyOfRange(arrayOfInt, i * arrayOfInt.length / countThreads, (i + 1) * arrayOfInt.length / countThreads);


            t[i] = new Thread(() -> {
                int[] args = arrayOfIntThreadSort[finalI];
                try {
                    finalShellMethod.invoke(null, new Object[]{args});
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            );
        }

        for (int i = 0; i < countThreads; i++)
            t[i].start();

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
