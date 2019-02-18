package org.shahin.nazarov.util.reflection;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Random {
    public static String randomToken(int length){
        SecureRandom random = new SecureRandom();
        int radix = 32;
        int bits = length < radix ? radix : length;
        String token = new BigInteger(bits, random).toString(radix).substring(0, length);
        return token;
    }
}
