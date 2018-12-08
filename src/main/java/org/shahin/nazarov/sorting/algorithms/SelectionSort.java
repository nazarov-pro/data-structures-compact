package org.shahin.nazarov.sorting.algorithms;

import org.shahin.nazarov.sorting.Sortable;

public class SelectionSort implements Sortable {

    @Override
    public void sort(int[] array) {
        int maxIndex;
        for (int i = array.length - 1; i > 0; i--) {
            maxIndex = 0;
            for(int j = 1; j <= i; j++){
                if(array[j] > array[maxIndex]){
                    maxIndex = j;
                }
            }
            swap(array, i, maxIndex);
        }
    }

    @Override
    public String toString() {
        return "SelectionSort";
    }
}
