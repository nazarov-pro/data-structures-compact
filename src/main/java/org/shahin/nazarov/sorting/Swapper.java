package org.shahin.nazarov.sorting;

public interface Swapper {

    default void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
