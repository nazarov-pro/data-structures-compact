package org.shahin.nazarov.sorting.algorithms;

import org.shahin.nazarov.sorting.Sortable;

public class ShellSort implements Sortable {

    public static final String INSERTION_SORT = "insertion";
    public static final String BUBBLE_SORT = "bubble";

    private String algorithm;

    public ShellSort(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void sort(int[] array) {
        switch (algorithm) {
            case INSERTION_SORT:
                sortInsertionMode(array);
                break;
            case BUBBLE_SORT:
                sortBubbleMode(array);
                break;
            default:
                throw new IllegalArgumentException("No such sorting algorithm found for " + algorithm);
        }
    }

    private void sortInsertionMode(int[] array) {
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                int element = array[j];
                int k = j;
                while (k >= i && array[k - i] > element) {
                    shift(array, k, -i);
                    k -= i;
                }
                array[k] = element;
            }

        }
    }

    private void sortBubbleMode(int[] array) {
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i - 1; j < array.length; j++) {
                for (int k = j + i; k < array.length; k++) {
                    if (array[j] > array[k]) {
                        swap(array, j, k);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("SellSort using %s algorithm", algorithm);
    }
}
