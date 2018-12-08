package org.shahin.nazarov.sorting.algorithms;

import org.shahin.nazarov.sorting.Sortable;

public class InsertionSort implements Sortable {

    @Override
    public void sort(int[] array) {
        int newElement;
        for (int i = 1; i < array.length; i++) {
            newElement = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > newElement; j--) {
                shift(array, j, -1);
            }
            array[j] = newElement;
        }
    }

    @Override
    public String toString() {
        return "InsertionSort";
    }
}
