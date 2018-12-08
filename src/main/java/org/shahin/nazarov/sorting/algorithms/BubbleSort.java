package org.shahin.nazarov.sorting.algorithms;

import org.shahin.nazarov.sorting.Sortable;

public class BubbleSort implements Sortable {

    @Override
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BubbleSort";
    }
}
