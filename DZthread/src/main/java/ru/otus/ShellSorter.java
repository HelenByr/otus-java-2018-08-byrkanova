package ru.otus;

public class ShellSorter implements SerialIntSorter {
    public void sort(int[] a)
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
