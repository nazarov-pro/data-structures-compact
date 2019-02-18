package org.shahin.nazarov.util;

import org.junit.Test;
import org.shahin.nazarov.util.reflection.Random;

public class RandomTest {

    @Test
    public void test(){
        for(int i =0; i< 100; i++){
            String token = Random.randomToken(5);
            System.out.println(token + " - " + token.length());
        }
    }
}
