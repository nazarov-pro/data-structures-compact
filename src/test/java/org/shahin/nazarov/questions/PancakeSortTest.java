package org.shahin.nazarov.questions;

import org.junit.Test;
import org.shahin.nazarov.util.TestHelper;

public class PancakeSortTest extends TestHelper {

    public void sort(int[] arr) {
        outer:for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = i;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }

            if(maxIndex == i){
              continue outer;
            }



            flip(arr, maxIndex + 1);
            flip(arr, i + 1);
        }
    }

    public void flip(int[] arr, int k) {
        if (arr == null || arr.length < 2 || arr.length < k) {
            return;
        }

        for (int i = 0; i < k / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[k - i - 1];
            arr[k - i - 1] = tmp;
        }
    }

    @Test
    public void test() {
        System.out.println("Used algorithm -> " + "Pancake Sorting");
        getTimer().start();
        int[] array = getArrayUtil().generateIntArray(50, 100);
        getArrayUtil().print(array);
        sort(array);
        getArrayUtil().print(array);
        getTimer().stop();
        System.out.println();
    }
}
