package org.shahin.nazarov.sorting;

import org.junit.Test;
import org.shahin.nazarov.util.TestHelper;

public class SortTest extends TestHelper {

    @Test
    public void bubbleSortTest(){
        Sortable bubbleSort = new BubbleSort();
        getTimer().start();
        int[] array = getArrayUtil().generateIntArray(15);
        getArrayUtil().print(array);
        bubbleSort.sort(array);
        getArrayUtil().print(array);
        getTimer().stop();
    }
}
