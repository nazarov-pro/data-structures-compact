package org.shahin.nazarov.util;

import java.util.Random;

public class RandomGenerator {

    public int[] generateIntArray(){
        return generateIntArray(10, 100);
    }

    public int[] generateIntArray(int size){
        return generateIntArray(size, 100);
    }

    public int[] generateIntArray(int size,int max){
        int[] array = new int[size];
        for(int i = 0; i< array.length; i++){
            array[i] = (int) (Math.random() * max);
        }
        return array;
    }
}
