package org.shahin.nazarov.sorting;

public interface SortingUtil {

    default void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    default void shift(int[] array, int i, int step) {
        array[i] = array[i + step];
    }

}
