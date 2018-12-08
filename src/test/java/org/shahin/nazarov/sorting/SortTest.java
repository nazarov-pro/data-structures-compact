package org.shahin.nazarov.sorting;

import org.junit.Test;
import org.shahin.nazarov.sorting.algorithms.*;
import org.shahin.nazarov.util.TestHelper;

public class SortTest extends TestHelper {

    public static final int LENGTH_OF_ARRAY = 1500;

    @Test
    public void bubbleSortTest(){
        sortIngTest(new BubbleSort());
    }

    @Test
    public void selectionSortTest(){
        sortIngTest(new SelectionSort());
    }

    @Test
    public void insertionSortTest(){
        sortIngTest(new InsertionSort());
    }

    @Test
    public void shellSortAsInsertionTest(){
        sortIngTest(new ShellSort(ShellSort.INSERTION_SORT));
    }

    @Test
    public void shellSortAsBubbleTest(){
        sortIngTest(new ShellSort(ShellSort.BUBBLE_SORT));
    }

    @Test
    public void mergeSortTest(){
        sortIngTest(new MergeSort());
    }

    private void sortIngTest(Sortable sort){
        System.out.println("Used algorithm -> " + sort.toString());
        getTimer().start();
        int[] array = getArrayUtil().generateIntArray(LENGTH_OF_ARRAY);
        getArrayUtil().print(array);
        sort.sort(array);
        getArrayUtil().print(array);
        getTimer().stop();
        System.out.println();
    }
}
