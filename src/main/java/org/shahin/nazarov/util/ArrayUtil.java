package org.shahin.nazarov.util;

public class ArrayUtil extends RandomGenerator {

    public void print(int[] array){
        System.out.print("array { ");
        for(int i = 0; i< array.length; i++){
            System.out.printf("%d ", array[i]);
        }
        System.out.println("}");
    }
}
